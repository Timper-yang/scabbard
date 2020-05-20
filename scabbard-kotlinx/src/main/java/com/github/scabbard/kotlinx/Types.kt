package com.github.scabbard.kotlinx

import java.util.*

/**
 * User: tangpeng.yang
 * Date: 2020/5/13
 * Description:
 * FIXME
 */
object Types {

    private final val SIGNATURES =
        arrayOf(
            AbstractMap.SimpleEntry(Boolean::class.javaPrimitiveType, "Z"),
            AbstractMap.SimpleEntry(Byte::class.javaPrimitiveType, "B"),
            AbstractMap.SimpleEntry(Char::class.javaPrimitiveType, "C"),
            AbstractMap.SimpleEntry(Short::class.javaPrimitiveType, "S"),
            AbstractMap.SimpleEntry(Int::class.javaPrimitiveType, "I"),
            AbstractMap.SimpleEntry(Float::class.javaPrimitiveType, "F"),
            AbstractMap.SimpleEntry(Double::class.javaPrimitiveType, "D"),
            AbstractMap.SimpleEntry(Long::class.javaPrimitiveType, "J"),
            AbstractMap.SimpleEntry(Void.TYPE, "V")
        ).associateBy({ it.key }, { it.value })


    fun getDescriptor(clazz: Class<*>): String {
        val signature = SIGNATURES[clazz]
        signature?.let {
            return it
        }
        if (clazz.isArray) {
            return "[${getDescriptor(clazz.componentType)}"
        }
        return "L${clazz.name.replace('.','/')};"
    }

}