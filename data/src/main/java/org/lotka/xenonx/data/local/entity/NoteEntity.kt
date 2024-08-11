package org.lotka.xenonx.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class NoteEntity (
    val title : String,
    val content : String,
    val timestamp:Long,
    val color:Int,
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null
)


fun NoteEntity.toNote():Note{
    return Note(
        title = title,
        content = content,
        timestamp = timestamp,
        color = color,
        id = id
    )
}

fun Note.NoteEntity(): NoteEntity {
    return NoteEntity(
        title = title,
        content = content,
        timestamp = timestamp,
        color = color,
        id = id
    )
}