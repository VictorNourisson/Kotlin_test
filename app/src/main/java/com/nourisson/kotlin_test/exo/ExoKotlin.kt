package com.nourisson.kotlin_test.exo

import okhttp3.OkHttpClient

fun main(){
    var v1:String = "toto"
    var v2:String? = null
    var v3:String? = null ?:"toto"
    println(v1)
    println(v2)
    println(v3)
    println(min1(4,3,10))
    println(min(7,5,10))
    println(pair(8))
    myPrint("oui")
    println(boulangerie(0,2,3))
    println(scanText("Comment vas-tu"))

}

fun min(a: Int, b:Int, c:Int) : Int{
    if(a<=b && a<=c){
        return a
    } else if(b<=a && b<=c){
        return b
    } else {
        return c
    }
}
fun min1(a: Int, b:Int, c:Int) = if(a<=b && a<=c) a  else if(b<=a && b<=c) b else c

fun pair(d: Int) = d % 2 == 0
fun myPrint(chaine: String) = println("#$chaine#")

fun boulangerie(nbCroissant: Int=0, nbBaguette: Int=0, nbSandwich: Int=0) =
    nbCroissant * prixCroissant + nbBaguette * prixBaguette + nbSandwich * prixSandwich

fun scanText(question:String): String {
    print(question)
    return readlnOrNull() ?: "-"
}

fun scanNumber(question:String) = scanText(question).toIntOrNull()

