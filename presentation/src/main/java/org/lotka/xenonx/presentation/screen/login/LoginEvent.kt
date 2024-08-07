package org.lotka.xenonx.presentation.screen.login

import androidx.compose.ui.focus.FocusState

sealed class LoginEvent {

     data class EnterUserName (val userName:String):LoginEvent()
     data class EnterPassword (val password:String):LoginEvent()
//     data class UserNameFocusChange(val focusState: FocusState):LoginEvent()
//     data class PasswordFocusChange(val focusState: FocusState):LoginEvent()

}