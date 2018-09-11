package com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts;

import android.util.Log;

public class DebugLog {

    public DebugLog() {
    }

    public static void log(String tag, String msg) {
        Log.e(tag, msg);
    }

}
