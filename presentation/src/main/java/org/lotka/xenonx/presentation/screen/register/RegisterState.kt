package org.lotka.xenonx.presentation.screen.register

import org.lotka.xenonx.domain.model.LoginResponse
import org.lotka.xenonx.domain.model.RegisterResponse

data class RegisterState (
    val userName: String = "",
    val password: String = "",
    val email: String = "",
    val isLoading: Boolean = false,
    val registerResponse: RegisterResponse? = null,
    val userNameError: String? = null,
    val passwordError: String? = null,
    val error: String? = null
)