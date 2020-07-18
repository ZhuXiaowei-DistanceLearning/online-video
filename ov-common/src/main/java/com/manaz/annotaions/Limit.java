package com.manaz.annotaions;

import java.lang.annotation.*;

/**
 * @author zxw
 * @date 2020/7/18 15:57
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limit {
    int count();
}
