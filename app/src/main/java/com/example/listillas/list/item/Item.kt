package com.example.listillas.list.item

import androidx.core.graphics.createBitmap
import java.text.SimpleDateFormat
import java.time.Instant

// al poner al final del parametro una ? indica que no es obligatorio
data class Item(
    //Obligatoria
    val title: String,

    // Obligatoria pero se inicializan
    val type: ItemTypes = ItemTypes.basic,
    val chek: Boolean = false,
    val creationDate: Long = Instant.now().toEpochMilli(),
    val modificationDate: Long = Instant.now().toEpochMilli(),

    //Opcionales
    val description: String?,
    val location: String?,
    val scheduledDate: Long?,
    val labels: List <String>,

    // Opcionales de tipo especial
    val media: String?,
    val list: List<SubItem>?
) {
    fun getCreationDate(): String {
        return getFormattedDate(creationDate)
    }

    fun getFormattedDate(date: Long): String {
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
}
