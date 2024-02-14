package com.nourisson.kotlin_test.exo

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    //Lazy loading + une seule instanciation
//    var html = WeatherAPI.sendGet("https://www.google.fr")
//    println(html)

    val weatherList = WeatherAPI.loadWeatherAround("Nantes")

    weatherList.forEach {weather->
        println("Il fait ${weather.main.temp}° à ${weather.name} avec un vent de ${weather.wind.speed} m/s\n${weather.weather.getOrNull(0)?.icon ?:"-Pas d'image"}")
    }

}

object WeatherAPI {
    val client = OkHttpClient()
    val gson = Gson()

    const val URL_SERVER = "https://api.openweathermap.org/data/2.5"

    fun loadWeatherAround(cityName: String): List<WeatherBean> {
        if (cityName.length < 3) {
            throw Exception("Il faut au moins 3 caractères")
        }

        val json = sendGet("$URL_SERVER/find?q=$cityName&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        val res = gson.fromJson(json, WeatherAroundResult::class.java)

        if(res.list.isEmpty()) {
            throw Exception("Pas de résultat")
        }

        //Ajout de l'url de l'image complète
        res.list.filter { it.weather.isNotEmpty() }.forEach {
            it.weather.get(0).icon = "https://openweathermap.org/img/wn/${it.weather.get(0).icon}@4x.png"
        }

        return res.list
    }

    fun loadWeather(cityName: String): WeatherBean {


        val json = sendGet("$URL_SERVER/weather?q=$cityName&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")


        return gson.fromJson(json, WeatherBean::class.java)
    }

    fun loadUser(): PersonneBean {

        val json = sendGet("https://www.amonteiro.fr/api/randomuser")

        return gson.fromJson(json, PersonneBean::class.java)
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

/* -------------------------------- */
// Beans
/* -------------------------------- */
data class WeatherAroundResult(val list: List<WeatherBean>)
data class WeatherBean(
    var main: TempBean,
    var name: String,
    var wind: WindBean,
    var weather: List<DescriptionBean>
)

data class TempBean(var temp: Double)
data class WindBean(var speed: Double)
data class DescriptionBean(
    val description: String,
    var icon: String,
)

/* -------------------------------- */
// RandomUSer
/* -------------------------------- */
data class PersonneBean(
    val age: Int,
    val coord: Coord?,
    val name: String
)

data class Coord(
    val mail: String?,
    val phone: String?
)