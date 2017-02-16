package com.rongxianren.lsn35_highui_path_test.pathMeasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rongxianren.lsn35_highui_path_test.R;

public class PathMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PathMeasureView pathMeasureView = new PathMeasureView(this);
        setContentView(pathMeasureView);
    }
}
