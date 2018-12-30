package com.quincy.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.quincy.core.Constants;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.TYPE})
public @interface Sharding {
	public String operation() default Constants.SHARDING_OPERATION_INSERT;
}
