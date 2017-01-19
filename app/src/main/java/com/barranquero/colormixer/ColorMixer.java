package com.barranquero.colormixer;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

/**
 * Created by usuario on 19/01/17
 * ColorMixer
 */

public class ColorMixer extends RelativeLayout {
    private SeekBar red, green, blue;
    private View swatch;
    private SeekBar.OnSeekBarChangeListener listener;
    private OnColorChangedListener colorChangedListener;

    public interface OnColorChangedListener {
        void OnColorChanged(int color);
    }

    public ColorMixer(Context context) {
        super(context);
    }

    public ColorMixer(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 1. Components view gets inflated
        ((Activity)getContext()).getLayoutInflater().inflate(R.layout.mixer, this, true);

        // 1.1 Listener gets initialised
        initSeekbarListener();

        // 2. Get references
        red = (SeekBar)findViewById(R.id.red);
        red.setMax(0xFF);
        red.setOnSeekBarChangeListener(listener);
        green = (SeekBar)findViewById(R.id.green);
        green.setMax(0xFF);
        green.setOnSeekBarChangeListener(listener);
        blue = (SeekBar)findViewById(R.id.blue);
        blue.setMax(0xFF);
        blue.setOnSeekBarChangeListener(listener);
        swatch = findViewById(R.id.swatch);

        if (attrs != null) {
            TypedArray myTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ColorMixer);
            int color = myTypedArray.getColor(R.styleable.ColorMixer_initColor, Color.BLACK);
            setColorSeekBar(color);
            myTypedArray.recycle();
        }
    }

    private void initSeekbarListener() {
        listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (colorChangedListener != null) {
                    // 1. The Seekbar values are obtained
                    int color = Color.rgb(red.getProgress(), green.getProgress(), blue.getProgress());

                    // 2. Modify swatch component
                    swatch.setBackgroundColor(color);

                    // 3. Event gets
                    colorChangedListener.OnColorChanged(color);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not used
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not used
            }
        };
    }

    public void setOnColorChangedListener(OnColorChangedListener listener) {
        this.colorChangedListener = listener;
    }

    private void setColorSeekBar(int color) {
        red.setProgress(Color.red(color));
        green.setProgress(Color.green(color));
        blue.setProgress(Color.blue(color));
        swatch.setBackgroundColor(color);
    }

    public int getColor() {
        return (Color.rgb(red.getProgress(), green.getProgress(), blue.getProgress()));
    }
}
