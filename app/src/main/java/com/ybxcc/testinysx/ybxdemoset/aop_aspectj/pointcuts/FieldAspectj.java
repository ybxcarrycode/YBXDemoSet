package com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class FieldAspectj {

    private static final String FIELD_ASPECT_GET = "get(long com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.StopWacth.time)";

    @Around(FIELD_ASPECT_GET)
    public long aroundFieldGet(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = joinPoint.proceed();
        long time = Long.parseLong(object.toString());
        Log.e("CC-As", "====  " + time);
        return 101;
    }


    private static final String FIELD_ASPECT_SET = "set(long com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.StopWacth.time && !withincode(com.ybxcc.testinysx.ybxdemoset.aop_aspectj..*.new(..)))";

    @Around(FIELD_ASPECT_SET)
    public void aroundFieldSet(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("CC-As_setField", "====  >>" + joinPoint.getSignature().getDeclaringType().getSimpleName());
    }


}
