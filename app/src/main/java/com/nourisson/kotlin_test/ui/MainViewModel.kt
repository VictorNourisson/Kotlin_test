package com.nourisson.kotlin_test.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nourisson.kotlin_test.exo.ChuckNorrisAPI
import com.nourisson.kotlin_test.exo.PictureBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val searchText = mutableStateOf("Chuck")
    val myList = mutableStateListOf<PictureBean>()
    val runInProgress = mutableStateOf(false)
    val errorMessage = mutableStateOf("")


    val selectedPicture = mutableStateOf<PictureBean?>(null)

    fun uploadSearchText(newText: String) {
        searchText.value = newText
    }

    fun loadData() {//Simulation de chargement de donnée

        runInProgress.value = true
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.Default) {

            try {
                val listChuckNorris = ChuckNorrisAPI.loadQuote(searchText.value)
                val listPicture = listChuckNorris.map {
                    PictureBean(
                        it.url,
                        it.value,
                        "Citation :",
                        it.iconUrl
                    )
                }
                myList.clear()
                myList.addAll(listPicture)

            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = "Erreur : ${e.message}"
            }
            runInProgress.value = false
        }

    }
}