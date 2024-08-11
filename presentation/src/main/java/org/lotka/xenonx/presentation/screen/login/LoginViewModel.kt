package org.lotka.xenonx.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.LoginUseCase
import org.lotka.xenonx.domain.util.Resource
import org.lotka.xenonx.presentation.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EnterPassword -> {
                _state.value = state.value.copy(
                    password = event.password)
            }
            is LoginEvent.EnterUserName -> {
                _state.value = state.value.copy(
                    userName = event.userName)
            }
            is LoginEvent.Login -> {
                login(state.value.userName, state.value.password)
            }
            is LoginEvent.ShowSnakeBar -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.ShowSnakeBar(event.message))
                }
            }

            is LoginEvent.ShowPassword -> {
                _state.value = state.value.copy(
                    showPassword = !state.value.showPassword
                )
            }

            is LoginEvent.PasswordError -> {
                _state.value = state.value.copy(
                  passwordError = event.passwordError
                )
            }
            is LoginEvent.UserNameError -> {
                _state.value = state.value.copy(
                   userNameError = event.userNameError
                )
            }
        }
    }

    private fun login(userName: String, password: String) {
        viewModelScope.launch {
            loginUseCase(userName, password).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            loginResponse = result.data,
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

