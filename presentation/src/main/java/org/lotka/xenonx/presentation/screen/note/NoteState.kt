package org.lotka.xenonx.presentation.screen.note

import org.lotka.xenonx.domain.model.Note
import org.lotka.xenonx.domain.util.NoteOrder

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.DATA,
    val isOrderSectionVisible: Boolean = false
)
