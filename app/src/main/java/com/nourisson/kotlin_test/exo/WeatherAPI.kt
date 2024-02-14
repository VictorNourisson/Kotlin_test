package com.nourisson.kotlin_test.exo

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object WeatherAPI {

    val gson = Gson()
    val client = OkHttpClient()
    const val URL_SERVER = "https://www.amonteiro.fr/api/randomuser"

    fun loadWeather(name:String): WeatherBean {
        //Eventuel contrôle
        //Réaliser la requête.
        val json: String = sendGet(URL_SERVER)

        //Parser le JSON avec le bon bean et GSON
        val data : WeatherBean = gson.fromJson(json, WeatherBean::class.java)

        //Eventuel contrôle ou extraction de données

        //Retourner la donnée
        return data
    }
    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

data class WeatherBean (
    var name :String,
    var coord :MailBean?,

)
data class MailBean (
    var phone :String?,
    var mail :String?
)


//Utilisation
fun main() {
    //Lazy loading + une seule instanciation
    var user = WeatherAPI.loadWeather("Jean-Phi")
    println("il s'appelle ${user.name} pour le contacter :")
    println("Phone : ${user.coord?.phone ?: "-"}")
    println("Mail : ${user.coord?.mail ?: "-"}")
}