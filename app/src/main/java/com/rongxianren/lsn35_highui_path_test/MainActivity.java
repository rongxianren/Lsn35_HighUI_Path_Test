package com.rongxianren.lsn35_highui_path_test;

import android.content.Intent;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rongxianren.lsn35_highui_path_test.bezierView.BezierActivity;
import com.rongxianren.lsn35_highui_path_test.pathMeasure.PathMeasureActivity;
import com.rongxianren.lsn35_highui_path_test.searchViewAnimation.SearchViewAnimationActivity;
import com.rongxianren.lsn35_highui_path_test.waveView.WaveViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bezierView(View view) {
        Intent intent = new Intent(this, BezierActivity.class);
        startActivity(intent);
    }

    public void waveView(View view) {
        Intent intent = new Intent(this, WaveViewActivity.class);
        startActivity(intent);
    }


    public void pathMeasure(View view) {
        Intent intent = new Intent(this, PathMeasureActivity.class);
        startActivity(intent);
    }

    public void searchAnimationView(View view) {
        Intent intent = new Intent(this, SearchViewAnimationActivity.class);
        startActivity(intent);
    }
}
