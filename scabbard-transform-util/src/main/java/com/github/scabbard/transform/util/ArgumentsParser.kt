package com.github.scabbard.transform.util

/**
 * User: tangpeng.yang
 * Date: 2020/5/15
 * Description:
 * FIXME
 */
class ArgumentsParser(private val signature: String, private val offset: Int = 0, private val length: Int = signature.length) {

    private val limit = offset + length

    private var pos = offset

    fun parse(): List<String> {
        val args = mutableListOf<String>()

        loop@ while (true) {
            val arg = parseArgument() ?: break
            args.add(arg)
        }

        return args.toList()
    }

    private fun parseArgument(): String? {
        val p = pos
        val c = nextChar()

        return when (c) {
            /* B */ 66 -> "byte"
            /* C */ 67 -> "char"
            /* D */ 68 -> "double"
            /* F */ 70 -> "float"
            /* I */ 73 -> "int"
            /* J */ 74 -> "long"
            /* L */ 76 -> parseQualifiedType()
            /* S */ 83 -> "short"
            /* V */ 86 -> "void"
            /* Z */ 90 -> "boolean"
            /* [ */ 91 -> parseArrayType()
            /*EOF*/ -1 -> null
            else -> throw IllegalArgumentException("unexpected char `${c.toChar()}` at $p")
        }
    }

    private fun parseArrayType() = parseArgument() + "[]"

    private fun parseQualifiedType(): String {
        var c: Int
        var p: Int
        val buf = StringBuilder()
        loop@ while (true) {
            p = pos
            c = nextChar()
            when (c) {
                /* ; */ 59 -> break@loop
                /*EOF*/ -1 -> throw java.lang.IllegalArgumentException("unexpected char `$c` at $p")
                /* / */ 47 -> buf.append('.')
                else -> buf.append(c.toChar())
            }
        }
        return buf.toString()
    }

    private fun nextChar() = if (pos < limit) signature[pos++].toInt() else -1

}
