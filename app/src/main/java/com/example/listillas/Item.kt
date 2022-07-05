package com.example.listillas

import java.time.LocalDateTime
import java.util.*

// al poner al final del parametro una ? indica que no es obligatorio
data class Item(
    val title: String,
    val description: String = "",
    val date: String = Date().toString(),
    var chek: Boolean = false)
