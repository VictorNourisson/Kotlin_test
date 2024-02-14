package com.nourisson.kotlin_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nourisson.kotlin_test.ui.MainViewModel
import com.nourisson.kotlin_test.ui.screens.DetailScreenChuck
import com.nourisson.kotlin_test.ui.screens.SearchScreenChuck
import com.nourisson.kotlin_test.ui.theme.Kotlin_testTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kotlin_testTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}
@Composable
fun AppNavigation() {

    val navController : NavHostController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    //Import version avec Composable
    NavHost(
        navController = navController,
        startDestination = Routes.SearchScreenChuck.route) {

        //Route 1 vers notre SearchScreen
        composable(Routes.SearchScreenChuck.route) {
            //on peut passer le navController à un écran s'il déclenche des navigations
            SearchScreenChuck(navController,  viewModel)
        }

        //Route 2 vers un écran de détail
        composable(
            route = Routes.DetailScreenChuck.route,
            arguments = listOf(navArgument("data") { type = NavType.IntType })
        ) {
            val position = it.arguments?.getInt("data") ?: 0
            DetailScreenChuck(position, navController, viewModel)
        }
    }
}

//sealed permet de dire qu'une classe est héritable (ici par SearchScreen et DetailScreen)
//Uniquement par les sous classes qu'elle contient
sealed class Routes(val route: String) {
    //Route 1
    object SearchScreenChuck : Routes("SearchScreenChuck")

    //Route 2 avec paramètre
    object DetailScreenChuck : Routes("detailScreenChuck/{data}") {
        //Méthode(s) qui vienne(nt) remplit le ou les paramètres
        fun withPosition(position: Int) = "detailScreenChuck/$position"

        fun first() = "detailScreenChuck/0"
    }
}

