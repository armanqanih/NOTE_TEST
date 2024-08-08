package org.lotka.xenonx.presentation.screen.login

import androidx.compose.ui.focus.FocusState

sealed class LoginEvent {

     data class EnterUserName (val userName:String):LoginEvent()
     data class EnterPassword (val password:String):LoginEvent()
     object Login : LoginEvent()
     data class ShowSnakeBar(val message:String):LoginEvent()

}