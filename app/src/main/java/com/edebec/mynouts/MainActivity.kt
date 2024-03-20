package com.edebec.mynouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.edebec.mynouts.ui.theme.MyNoutsTheme
import com.edebec.mynouts.view.ui.MyNoutsApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoutsTheme {
                MyNoutsApp()
            }
        }
    }
}