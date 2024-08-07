package org.lotka.xenonx.presentation.screen.login

import org.lotka.xenonx.domain.model.LoginResponse

data class LoginState (
    val userName: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginResponse: LoginResponse? = null,
    val userNameError: String? = null,
    val passwordError: String? = null,
    val error: String? = null
)