package com.github.scabbard.annotations

/**
 * User: tangpeng.yang
 * Date: 2020/5/15
 * Description:
 * FIXME
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Priority(val value: Int = 0)