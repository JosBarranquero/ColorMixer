package com.barranquero.colormixer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ColorMixer colorMixer;
    private TextView txvColor;
    private ColorMixer.OnColorChangedListener colorChangedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorMixer = (ColorMixer)findViewById(R.id.mixer);
        colorChangedListener = new ColorMixer.OnColorChangedListener() {
            @Override
            public void OnColorChanged(int color) {
                txvColor.setText(Integer.toHexString(color));
            }
        };
        colorMixer.setOnColorChangedListener(colorChangedListener);

        txvColor = (TextView)findViewById(R.id.txvColor);
        txvColor.setText(Integer.toHexString(colorMixer.getColor()));
    }
}
