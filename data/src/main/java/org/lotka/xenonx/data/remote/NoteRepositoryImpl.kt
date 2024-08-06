package org.lotka.xenonx.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lotka.xenonx.data.local.dao.NoteDao
import org.lotka.xenonx.data.local.entity.NoteEntity
import org.lotka.xenonx.data.local.entity.toNote
import org.lotka.xenonx.domain.model.Note
import org.lotka.xenonx.domain.repository.NoteRepository
import javax.inject.Inject


class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
):NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return flow {
            val notes = dao.getNotes().map { it.toNote() }
            emit(notes)
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.toNote()
    }

    override suspend fun insertNote(note: Note) {
       return dao.insertNote(note.NoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note.NoteEntity())
    }


}