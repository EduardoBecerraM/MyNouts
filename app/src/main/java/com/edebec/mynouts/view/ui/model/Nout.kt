package com.edebec.mynouts.view.ui.model

data class Nout(
    val id: Long,
    val title: String,
    val description: String,
    val datetime: String,
    val isChecked: Boolean
)
