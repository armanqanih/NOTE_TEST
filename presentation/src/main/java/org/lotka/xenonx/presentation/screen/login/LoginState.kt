package org.lotka.xenonx.presentation.screen.login

import org.lotka.xenonx.domain.model.LoginResponse

data class LoginState (
    val userName: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginResponse: LoginResponse? = null,
    val userNameError: String = "",
    val passwordError: String = "",
    val showPassword: Boolean = false,
    val error: String? = null
)