package com.ybxcc.testinysx.ybxdemoset.aop_aspectj;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ybxcc.testinysx.ybxdemoset.R;
import com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.DebugLog;
import com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.DebugTrace;
import com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.StopWacth;


public class AspectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspectj_test_layout);

        TextView tv = findViewById(R.id.tv_go);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldGetAs();
//                testAnnotatedMethod();
            }
        });
    }


    @DebugTrace
    private void testAnnotatedMethod() {
        DebugLog.log("CC_", ">>>>>>1");
        try {
            Thread.sleep(18);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void fieldGetAs() {
        StopWacth stopWacth = new StopWacth();

        long time = stopWacth.getTome();
        DebugLog.log("CC_", ">>>>>>" + time);

    }


}
