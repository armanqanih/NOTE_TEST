package org.lotka.xenonx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.lotka.xenonx.data.local.dao.NoteDao
import org.lotka.xenonx.data.local.entity.NoteEntity
import org.lotka.xenonx.domain.model.Note

@Database(
    entities = [NoteEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}