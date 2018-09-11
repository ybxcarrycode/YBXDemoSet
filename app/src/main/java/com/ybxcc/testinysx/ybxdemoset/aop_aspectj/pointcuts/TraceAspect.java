package com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TraceAspect {

    private static final String POINTCUT_METHOD_EXECUTION =
            "execution(@com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.DebugTrace * *..*.*(..))";

    private static final String POINTCUT_CONSTRUCTOR_EXECUTION =
            "execution(@com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.DebugTrace *.new(..))";

    private static final String POINTCUT_METHOD_CALL =
            "call(@com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.DebugTrace * *..*.*(..))";

    private static final String POINTCUT_CONSTRUCTOR_CALL =
            "call(@com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts.DebugTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD_CALL)
    public void methodAnnotatedWithDebugTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR_CALL)
    public void constructorAnnotatedDebugTrace() {
    }

    @Around("methodAnnotatedWithDebugTrace()||constructorAnnotatedDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWacth stopWacth = new StopWacth();
        stopWacth.start();
        Object result = joinPoint.proceed();
        stopWacth.stop();

        DebugLog.log(className, buildLogMessage(methodName, stopWacth.getTotalTime()));

        return result;
    }


//    @Pointcut("call(* com.ybxcc.testinysx.ybxdemoset.aop_aspectj.*..* *(..))")
//    public void callMethod() {
//    }
//
//    @Before("callMethod()")
//    public void beforeCallMethod(JoinPoint joinPoint) {
//        DebugLog.log("CC_", "before->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
//    }


//    @Around("execution(* com.ybxcc.testinysx.ybxdemoset.aop_aspectj.AspectActivity.*(..))")
//    public void onActivityMethodAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature methodSignature1 = (MethodSignature) joinPoint.getSignature();
//        String className = methodSignature1.getDeclaringType().getSimpleName();
//        String methodName = methodSignature1.getName();
//
//        final StopWacth stopWacth = new StopWacth();
//        stopWacth.start();
//        Object result = joinPoint.proceed();
//        stopWacth.stop();
//
//        DebugLog.log("CC-", buildLogMessage(methodName, stopWacth.getTotalTime()));
//    }


//    @Before("execution(* com.ybxcc.testinysx.ybxdemoset.aop_aspectj.AspectActivity.*())")
//    public void onMethodBefore(JoinPoint joinPoint) {
//        String key = joinPoint.getSignature().getName();
//        Log.e("CC-", "before ->" + key);
//    }


    private static String buildLogMessage(String methodName, long methodDuration) {
        StringBuilder message = new StringBuilder();
        message.append("CC - - >");
        message.append(methodName);
        message.append(" - - >");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");
        return message.toString();
    }
}
