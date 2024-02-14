package com.nourisson.kotlin_test.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.nourisson.kotlin_test.R
import com.nourisson.kotlin_test.exo.PictureBean
import com.nourisson.kotlin_test.exo.pictureList
import com.nourisson.kotlin_test.ui.theme.Kotlin_testTheme


//Code affiché dans la Preview, thème claire, thème sombre
@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    Kotlin_testTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            SearchScreen()
        }
    }
}

//Composable représentant l'ensemble de l'écran
@Composable
fun SearchScreen() {

    //Couleur à retirer lors de l'utilisation des thèmes de couleur
    Column(modifier = Modifier.background(Color.LightGray)) {
        SearchBar()
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(pictureList.size) {
                PictureRowItem(data = pictureList[it])
            }
        }
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            Button(
                onClick = { /* Do something! */ },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Clear filter")
            }
            Button(
                onClick = { /* Do something! */ },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Load data")
            }
        }
    }
}
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    //TODO

        TextField(
            value = "", //Valeur par défaut
            onValueChange = { newValue -> }, //Action
            leadingIcon = { //Image d'icone
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            },
            label = { Text("Enter text") }, //Texte d'aide qui se déplace
            placeholder = { //Texte d'aide qui disparait
                //Dans string.xml
                //Text(stringResource(R.string.placeholder_search))
                //En dur
                Text("Recherche")
            },
            //Comment le composant doit se placer
            modifier = modifier
                .fillMaxWidth() // Prend toute la largeur
                .heightIn(min = 56.dp) //Hauteur minimum
        )

}

//Composable affichant 1 PictureBean
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureBean) {
    //TODO

        Row() {

            GlideImage(
                model = data.url,
                //Dans string.xml
                //contentDescription = getString(R.string.picture_of_cat),
                //En dur
                contentDescription = "une photo de chat",
                loading = placeholder(R.mipmap.ic_launcher_round), // Image de chargement
                // Image d'échec. Permet également de voir l'emplacement de l'image dans la Preview
                failure = placeholder(R.mipmap.ic_launcher),
                contentScale = ContentScale.Fit,
                //même autres champs qu'une Image classique
                modifier = Modifier
                    .heightIn(max = 100.dp) //Sans hauteur il prendra tous l'écran
                    .widthIn(max = 100.dp)
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)) {
                Text(
                    text = data.text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = data.longText.take(20),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

