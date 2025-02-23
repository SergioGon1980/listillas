package com.example.listillas.list.item


import com.example.listillas.list.item.ItemTypes
import com.example.listillas.list.item.SubItem
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
    val description: String? = null,
    val location: String? = null,
    val scheduledDate: Long? = null,
    val labels: List <String> = emptyList(),

    // Opcionales de tipo especial
    val media: String? = null,
    val list: List<SubItem>?  = emptyList()
) {
    fun getCreationDate(): String {
        return getFormattedDate(creationDate)
    }

    fun getFormattedDate(date: Long): String {
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
}
