package com.ybxcc.testinysx.ybxdemoset.test_textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

import com.ybxcc.testinysx.ybxdemoset.R;

public class TestTextViewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_textview_drawable);

        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setGravity(Gravity.CENTER);
    }
}
