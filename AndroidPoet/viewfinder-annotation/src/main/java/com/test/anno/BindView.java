package com.test.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xuguohong on 2018/6/20.
 */

//CLASS 为编译前注解执行
@Retention(RetentionPolicy.CLASS)
//目标为属性注解
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
