package com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts;

import android.Manifest;

import com.ybxcc.testinysx.ybxdemoset.MainActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class PermissonAspect {

    @Around("execution(@com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.APermisson * *..*.*(..)) && @annotation(permisson)")
    public void checkPermisson(ProceedingJoinPoint joinPoint, APermisson permisson) throws Throwable {
        String permissonS = permisson.value();
        MainActivity mainActivity = (MainActivity) joinPoint.getThis();
//        Utils.requestPermisson(mainActivity, Manifest.permission.CAMERA).callback(new Callback(){
//            public void onGranted(){
//                try {
//                    // 继续执行原方法
//                    joinPoint.proceed();
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//            }
//            public void onDenied() {}
//        });


    }

}
