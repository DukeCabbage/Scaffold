package com.cabbage.scaffold

@Suppress("unused")
class Certain {

    fun main() {
        findMax(4, 6, 2, 0, 1, comparator = this::compare)
    }

    inline fun findMax(vararg ints: Int, comparator: (Int, Int) -> Int): Int {
        if (ints.isEmpty()) throw RuntimeException()

        var max = ints[0]
        for (it in ints) {
            max = comparator(max, it)
        }
        return max
    }

    fun compare(a: Int, b: Int): Int {
        return if (a > b) a else b
    }
}