package com.nourisson.kotlin_test.ui.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.nourisson.kotlin_test.Routes
import com.nourisson.kotlin_test.exo.PictureBean
import com.nourisson.kotlin_test.ui.MainViewModel
import com.nourisson.kotlin_test.ui.MyError
import com.nourisson.kotlin_test.ui.MyTopBar
import com.nourisson.kotlin_test.ui.theme.Kotlin_testTheme
import com.nourisson.kotlin_test.ui.theme.md_theme_light_inversePrimary

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenChuckPreview() {
    Kotlin_testTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            SearchScreenChuck()
        }
    }
}

@Composable
fun SearchScreenChuck(
    navController: NavHostController? = null,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val filterList = viewModel.myList.filter {
        it.quote.contains(viewModel.searchText.value, true)
    }

    LaunchedEffect("") {
        viewModel.loadData()
    }

    //Couleur à retirer lors de l'utilisation des thèmes de couleur
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.outline)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        MyTopBar()
        SearchBar(searchText = viewModel.searchText)

        Spacer(Modifier.size(8.dp))

        //Avec Animation
        AnimatedVisibility(visible = viewModel.runInProgress.value) {
            CircularProgressIndicator()
        }

        MyError(errorMessage = viewModel.errorMessage.value)

//        if(viewModel.runInProgress.value) {
//
//        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {

            items(filterList.size) {
                PictureRowChuckItem(data = filterList[it],
                onPictureClic = {
                    //Si on utilise le viewModel pour garder la valeur
                    viewModel.selectedPicture.value = filterList[it]

                    navController?.navigate(Routes.DetailScreenChuck.withPosition(it))
                }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = { viewModel.uploadSearchText("") },
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

            Spacer(Modifier.size(8.dp))

            Button(
                onClick = { viewModel.loadData() },
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
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowChuckItem(
    modifier: Modifier = Modifier,
    data: PictureBean,
    onPictureClic: () -> Unit = { }
) {

    var expended = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(md_theme_light_inversePrimary)
    ) {

//Permission Internet nécessaire
        Text(
            text = data.idQuote,
            modifier = Modifier
                .heightIn(max = 100.dp) //Sans hauteur il prendra tous l'écran
                .widthIn(max = 100.dp)
                .clickable {
                    onPictureClic()
                }
        )
            Text(
                text = data.quote,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.animateContentSize()
            )

    }
}
@Composable
fun SearchBar(modifier: Modifier = Modifier, searchText: MutableState<String>) {

    TextField(
        value = searchText.value, //Valeur par défaut
        onValueChange = { newValue ->
            searchText.value = newValue

        }, //Action
        leadingIcon = { //Image d'icone
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        label = { Text("Enter text") }, //Texte d'aide qui se déplace
        //Comment le composant doit se placer
        modifier = modifier
            .fillMaxWidth() // Prend toute la largeur
            .heightIn(min = 56.dp) //Hauteur minimum
    )
}