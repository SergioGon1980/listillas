package com.example.listillas.entities

import com.example.listillas.list.item.ItemTypes
import com.example.listillas.list.item.SubItem
import java.time.Instant

data class Note (
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
    val list: List<SubItem>?  = emptyList())
