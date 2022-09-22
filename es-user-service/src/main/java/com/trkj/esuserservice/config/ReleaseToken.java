package com.trkj.esuserservice.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author     ：byH
 * date       ：Created 2021/2/24 14:14
 * description：放行token
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReleaseToken {
}

