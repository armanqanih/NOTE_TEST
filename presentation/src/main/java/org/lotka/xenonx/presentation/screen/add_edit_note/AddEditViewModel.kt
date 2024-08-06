package org.lotka.xenonx.presentation.screen.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.model.Note
import org.lotka.xenonx.domain.usecase.AllNotesUseCases
import org.lotka.xenonx.domain.usecase.validation.InvalidateNoteException
import org.lotka.xenonx.presentation.composable.NoteColor
import org.lotka.xenonx.presentation.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val useCases: AllNotesUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    private val _noteTitle = mutableStateOf(AddEditState(hint = "Enter Title ... "))
    val noteTitle: State<AddEditState> = _noteTitle

    private val _noteContent = mutableStateOf(AddEditState(hint = "Enter Some Content "))
    val noteContent: State<AddEditState> = _noteContent

    private val _noteColor = mutableStateOf(NoteColor.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor


    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    useCases.getNoteById(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = _noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = _noteTitle.value.copy(
                    text = event.value
                )
            }

            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    if (_noteTitle.value.text.isNotEmpty() || _noteContent.value.text.isNotEmpty()) {
                        try {
                            useCases.addNote(
                                Note(
                                    title = noteTitle.value.text,
                                    content = noteContent.value.text,
                                    color = noteColor.value,
                                    timestamp = System.currentTimeMillis(),
                                    id = currentNoteId
                                )
                            )
                            _eventFlow.emit(UiEvent.SaveNote)
                        } catch (e: InvalidateNoteException) {
                            _eventFlow.emit(
                                UiEvent.ShowSnakeBar(
                                    message = e.message ?: "Couldn't save note"
                                )
                            )
                        }
                    }else{
                        _eventFlow.emit(
                            UiEvent.ShowSnakeBar(
                                message = "Please Add Title And Some Content"
                            ))
                    }
                }
            }
        }
    }


}