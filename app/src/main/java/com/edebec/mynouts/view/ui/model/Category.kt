package com.edebec.mynouts.view.ui.model

import androidx.compose.ui.graphics.Color

data class Category(
    val id: Long,
    val name: String,
    val containerColor: Color,
    val textColor: Color
)