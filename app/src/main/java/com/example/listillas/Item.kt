package com.example.listillas

import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// al poner al final del parametro una ? indica que no es obligatorio
data class Item(
    val title: String,
    val description: String = "",
    val date: String = Date().toString(),
    var chek: Boolean = false
) {
    /*fun getFormattedDate(): String {
        val format = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return LocalDate.parse(date, format).toString()
    }*/
}
