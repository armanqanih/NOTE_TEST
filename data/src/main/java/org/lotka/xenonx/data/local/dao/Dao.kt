package org.lotka.xenonx.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.lotka.xenonx.data.local.entity.NoteEntity

import org.lotka.xenonx.domain.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}