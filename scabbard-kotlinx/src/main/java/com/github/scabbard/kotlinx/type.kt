package com.github.scabbard.kotlinx

import java.lang.reflect.Method

val Class<*>.descriptor: String
    get() = Types.getDescriptor(this)

val Method.descriptor: String
    get() = parameters.joinToString("", "(", ")${returnType.descriptor}") {
        it.type.descriptor
    }
