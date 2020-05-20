package com.github.scabbard.transform

/**
 * User: tangpeng.yang
 * Date: 2020/5/18
 * Description:
 * FIXME
 */
interface Transformer : TransformListener {

    /**
     * Returns the transformed bytecode
     *
     * @param context
     *         The transforming context
     * @param bytecode
     *         The bytecode to be transformed
     * @return the transformed bytecode
     */
    fun transform(context: TransformContext, bytecode: ByteArray): ByteArray

}
