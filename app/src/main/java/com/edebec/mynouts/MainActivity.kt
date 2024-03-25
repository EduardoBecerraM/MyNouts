package com.edebec.mynouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.edebec.mynouts.ui.theme.MyNoutsTheme
import com.edebec.mynouts.view.ui.HomeScreen
import com.edebec.mynouts.view.ui.NewNoutScreen
import com.edebec.mynouts.view.ui.model.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoutsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navHostController = rememberNavController()

                    NavHost(navController = navHostController, startDestination = Routes.TodayScreen.id) {
                        composable(Routes.TodayScreen.id) { HomeScreen(navHostController = navHostController) }
                        composable(
                            Routes.NewNoutScreen.id,
                            arguments = listOf(navArgument("noutId") { type = NavType.IntType })
                        ) { navBackStackEntry ->
                            NewNoutScreen(navHostController = navHostController, noutId = navBackStackEntry.arguments?.getInt("noutId") ?: 0)
                        }
                    }
                }
            }
        }
    }
}