package org.lotka.xenonx.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.lotka.xenonx.domain.model.Note

import org.lotka.xenonx.domain.repository.NoteRepository
import org.lotka.xenonx.domain.util.NoteOrder
import org.lotka.xenonx.domain.util.NoteOrder.*


class GetNotesUseCase(
    private val repository: NoteRepository,
) {
     operator fun invoke(
        noteOrder: NoteOrder = DATA,
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (noteOrder) {
                DATA -> notes.sortedBy { it.timestamp }
                COLOR -> notes.sortedBy { it.color }
                TITLE -> notes.sortedBy { it.title }
            }
        }
    }
}