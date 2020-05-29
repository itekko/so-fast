package com.sofast.common.annotation;

import java.lang.annotation.*;

/**
 * @author ekko
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logs {
	String value() default "";
}
