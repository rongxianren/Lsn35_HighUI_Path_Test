package com.rongxianren.lsn35_highui_path_test.waveView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rongxianren.lsn35_highui_path_test.R;

public class WaveViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_view);
    }

    public void waveView(View view) {
        WaveView waveView = (WaveView) view;
        waveView.startAnimation();
    }
}
