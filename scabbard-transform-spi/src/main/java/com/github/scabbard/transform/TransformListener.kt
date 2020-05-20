package com.github.scabbard.transform

/**
 * User: tangpeng.yang
 * Date: 2020/5/18
 * Description:
 * FIXME
 */
interface TransformListener {

    fun onPreTransform(transformContext: TransformContext) {

    }

    fun onPostTransform(transformContext: TransformContext) {

    }
}
