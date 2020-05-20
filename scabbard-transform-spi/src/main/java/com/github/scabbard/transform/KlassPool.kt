package com.github.scabbard.transform

import java.io.Closeable

/**
 * User: tangpeng.yang
 * Date: 2020/5/18
 * Description:
 * FIXME
 */
interface KlassPool : Closeable {

    /**
     * Returns the parent
     */
    val parent: KlassPool?

    /**
     * Returns the class loader
     */
    val classLoader: ClassLoader

    /**
     * Returns an instance [Klass]
     *
     * @param type the qualified name of class
     */
    operator fun get(type: String): Klass

}
