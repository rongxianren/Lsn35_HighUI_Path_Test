package com.rongxianren.lsn35_highui_path_test.searchViewAnimation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import com.rongxianren.lsn35_highui_path_test.R;

public class SearchViewAnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchAnimationView view = new SearchAnimationView(this);
        setContentView(view);
    }
}
