package com.example;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by xuguohong on 2018/6/19.
 */

@Retention(CLASS)
@Target(METHOD)
public @interface Code {
    public String author();
    public String date() default "";
}