package com.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记【不推荐】的类或方法
 */
@Target(ElementType.TYPE)
//RetentionPolicy.SOURCE 标识一下，编译时忽略
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {

    String value() default  "";
}
