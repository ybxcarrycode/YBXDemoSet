package com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts;

import org.aspectj.lang.annotation.Aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
public @interface APermisson {
    String value();
}
