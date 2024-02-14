package com.nourisson.kotlin_test.exo



fun test (texte: String): String {
    var result=""
    for (c in texte)
    {
        if (c != 'o') {
            result += c
        }
    }
    return result
}

fun main() {
    println(test("Victor Nourisson le bo goss"))
}