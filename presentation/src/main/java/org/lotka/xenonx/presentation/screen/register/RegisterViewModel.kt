package org.lotka.xenonx.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.LoginUseCase
import org.lotka.xenonx.domain.usecase.SighUpUseCase
import org.lotka.xenonx.domain.util.Resource
import org.lotka.xenonx.presentation.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val sighUpUsecase: SighUpUseCase,

) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.EnterPassword -> {
                _state.value = state.value.copy(
                    password = event.password)
            }
            is RegisterEvent.EnterUserName -> {
                _state.value = state.value.copy(
                    userName = event.userName)
            }
            is RegisterEvent.Register -> {
                viewModelScope.launch {
                    sighUp(state.value.userName, state.value.password, state.value.email)
                    _eventFlow.emit(UiEvent.ShowSnakeBar("You have successfully registered"))
                }
            }
            is RegisterEvent.ShowSnakeBar -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.ShowSnakeBar(event.message))
                }
            }

            is RegisterEvent.EnterEmail -> {
                _state.value = _state.value.copy(
                    email = event.email
                )
            }
        }
    }

    private fun sighUp(userName: String, password: String,email:String) {
        viewModelScope.launch {
            sighUpUsecase(userName, password,email).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            registerResponse = result.data,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(isLoading = false)
                        _state.value = state.value.copy(
                            error = result.message,

                        )


                        }
                    }
                }
            }
        }

    }

