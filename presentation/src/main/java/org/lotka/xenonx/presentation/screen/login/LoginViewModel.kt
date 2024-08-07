package org.lotka.xenonx.presentation.screen.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel(){


    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()


    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EnterPassword -> {
                _state.value = state.value.copy(
                    password = event.password
                )
            }
            is LoginEvent.EnterUserName -> {
                _state.value = state.value.copy(
                    userName = event.userName
                )
            }


        }
    }

}