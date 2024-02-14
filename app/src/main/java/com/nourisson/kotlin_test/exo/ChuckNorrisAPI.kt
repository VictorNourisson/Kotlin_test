package com.nourisson.kotlin_test.exo

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request

object ChuckNorrisAPI {
    val client = OkHttpClient()
    val gson = Gson()

    fun loadQuote(quote: String): List<ChuckNorrisQuote> {

        if (quote.length < 3) {
            throw Exception("Il faut au moins 3 caractères")
        }

        val json = sendGet("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/search?query=${quote.lowercase()}")

        val res = gson.fromJson(json, ChuckNorrisQuoteList::class.java)

        if(res.result.isEmpty()) {
            throw Exception("Pas de résultat")
        }

        res.result.filter { it.url.isNotEmpty() }.forEach {
            it.url = "http://api.chucknorris.io/jokes/${it.id}"
        }


        return res.result
    }
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("accept", "application/json")
            .addHeader("X-RapidAPI-Key", "5589815abamsh28b38f7c5a7b6abp1b7bccjsn1b31527e07d9")
            .addHeader("X-RapidAPI-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
            .build()

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
data class ChuckNorrisQuoteList(val result: List<ChuckNorrisQuote>)
data class ChuckNorrisQuote(
    @SerializedName("icon_url")
    val iconUrl: String,
    val id: String,
    val value: String,
    var url: String,

)

fun main() {
    val chuckNorrisQuote = ChuckNorrisAPI.loadQuote("Chuck")
    //val test = SuperHeroAPI.loadRandomHero()
    println(chuckNorrisQuote)

}