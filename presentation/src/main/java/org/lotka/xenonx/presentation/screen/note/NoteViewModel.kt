package org.lotka.xenonx.presentation.screen.note
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.model.Note
import org.lotka.xenonx.domain.usecase.AllNotesUseCases
import org.lotka.xenonx.domain.util.NoteOrder
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val useCase: AllNotesUseCases,
) : ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    private var recenDeletertNote: Note? = null

    private var noteJob : Job? = null


    init {
        getNotes(NoteOrder.DATA)
    }


    fun onEvent(event: NoteEvent) {
        when (event) {
            NoteEvent.CancelDeleting -> {
                viewModelScope.launch {
                    useCase.addNote(recenDeletertNote ?: return@launch)
                    recenDeletertNote = null
                }
            }

            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    useCase.deleteNote(event.note)
                    recenDeletertNote = event.note
                }
            }

            is NoteEvent.Order -> {
             if (state.value.noteOrder::class == event.noteOrder::class)
                 return
                viewModelScope.launch {

                }
            }

            NoteEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }


    fun getNotes(noteOrder: NoteOrder){
          noteJob?.cancel()
         noteJob = useCase.getNotes(noteOrder).onEach {notes->
             _state.value = _state.value.copy(
                 notes = notes,
                 noteOrder = noteOrder
             )

         }.launchIn(scope = viewModelScope)

    }


}