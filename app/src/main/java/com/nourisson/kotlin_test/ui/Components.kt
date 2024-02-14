package com.nourisson.kotlin_test.ui


import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nourisson.kotlin_test.ui.theme.Kotlin_testTheme

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyErrorPreview() {
    Kotlin_testTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            MyError(errorMessage = "Coucou")
        }
    }
}

@Composable
fun MyError(modifier: Modifier = Modifier, errorMessage: String) {

    AnimatedVisibility(
        modifier = modifier,
        visible = errorMessage.isNotBlank()) {
        Text(
            modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.error).padding(8.dp),
            color = MaterialTheme.colorScheme.onError,
            text = errorMessage,
            textAlign = TextAlign.Center

        )
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title:String = "Chuck Norris",

) {

    TopAppBar(
        title = { Text(text = title ?: "") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),


    )
}