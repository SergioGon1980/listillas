package com.example.listillas.item

import java.text.SimpleDateFormat
import java.time.Instant

// al poner al final del parametro una ? indica que no es obligatorio
data class Item(
    val title: String,
    val description: String = "",
    val date: Long = Instant.now().toEpochMilli() ,
    var chek: Boolean = false
) {
    fun getFormattedDate(): String {
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
}
