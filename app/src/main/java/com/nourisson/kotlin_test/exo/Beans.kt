package com.nourisson.kotlin_test.exo

import java.util.Random

data class CarBean(var marque:String="", var model:String="")

class Housebean(var couleur:String, largeur:Int, longueur:Int) {
    var surface = largeur * longueur
    fun print() = println("la maison $couleur fait $surface")
}

class TownBean(var city:String, var cp:String) {
    var country:String = ""
}

data class DataTownBean(var city:String, var cp:String) {
    var country:String = ""
}

class PrintRandomIntBean(var max:Int) {
    private var random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }
    constructor() :this(100) {
        println(random.nextInt(max))
    }
}



fun main() {
    val voiture = CarBean("Seat", "Ibiza")
    println("C'est un ${voiture.marque} de model ${voiture.model}")
    val maison = Housebean("bleue", 4, 3)
    val townBean = TownBean("cirieres", "79140")
    maison.print()
    val dataTownBean = DataTownBean("bressuire", "79300")
    println(dataTownBean)

    PrintRandomIntBean()

    val randomName = RandomName()
    randomName.add("axel")
    repeat(10) {
        println(randomName.next() + " ")
    }

}

class RandomName() {

    fun add(nom:String) {
        if (nom.isNotBlank()) {
            name.add(nom)
        }
    }

    fun next() =  name.random()


    private var name = arrayListOf("jude", "xavier", "shawn")

}

const val LONG_TEXT = """Le Lorem Ipsum est simplement
    du faux texte employé dans la composition
    et la mise en page avant impression.
    Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""

data class PictureBean(val url: String, val text: String, val longText: String)

//jeu de donnée
val pictureList = arrayListOf(PictureBean("https://picsum.photos/200", "ABCD", LONG_TEXT),
    PictureBean("https://picsum.photos/201", "BCDE", LONG_TEXT),
    PictureBean("https://picsum.photos/202", "CDEF", LONG_TEXT),
    PictureBean("https://picsum.photos/203", "EFGH", LONG_TEXT)
)

