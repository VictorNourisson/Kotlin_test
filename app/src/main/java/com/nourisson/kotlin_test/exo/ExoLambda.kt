package com.nourisson.kotlin_test.exo

fun exo1() {

    var lower = { texte:String -> println("${texte.lowercase()}") }
    val heure : (Int) -> Float = { nombre:Int -> nombre/60.0F}
    val max : (Int, Int) -> Int = { nb1, nb2 -> Math.max(nb1, nb2)}
    val reverse : (String) -> String = {texte:String -> texte.reversed()}

    lower("SAluT")
    println(heure(420))
    println(max(5,8))
    println(reverse("essog uaeb el rotciv"))
}

data class UserBean(var name:String, var age:Int) {

}
fun compareUsers (u1: UserBean, u2: UserBean, u3: UserBean, block: (UserBean, UserBean)->UserBean): UserBean = block(u1, u2)

fun exo2() {
    val compareUsersByName :(UserBean, UserBean)->UserBean = {u1, u2 -> if( u1.name.lowercase() <= u2.name.lowercase()) u1 else u2 }
    val compareUsersByOld :(UserBean, UserBean)->UserBean = {u1, u2 -> if( u1.age >= u2.age) u1 else u2 }

    val u1 = UserBean("Bob", 19)
    val u2 = UserBean("Toto", 45)
    val u3 = UserBean("Charles", 26)
    println(compareUsers(u1, u2, u3, compareUsersByName)) // UserBean(name=Bob old=19)
    println(compareUsers(u1, u2, u3, compareUsersByOld)) // UserBean(name=Toto old=45)

}

fun exo4(){
    val users = arrayListOf(
        UserBean ("toto", 10),
        UserBean ("Bobby", 5),
        UserBean ("Charles", 10))

    var res1 = users.filter { it.age >=10 }
    var moyenne = users.map { (it.age)}.average()
    var res2 = users.count { it.name.equals("toto", true) && it.age >=10 }
    var res3 = users.filter { it.age >=moyenne }
    var res4 = users.distinctBy { it.name}
    var res5 = users.map { it.age + 1 }
    println(res3)
}


fun main() {
    // exo1()
    exo4()
}