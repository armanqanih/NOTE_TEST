package org.lotka.xenonx.domain.model

import com.google.type.Color


data class Note(
    val title : String,
    val content : String,
    val timestamp:Long,
    val color:Int,
    val id:Int? = null
)
