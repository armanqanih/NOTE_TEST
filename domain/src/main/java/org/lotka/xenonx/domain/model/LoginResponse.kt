package org.lotka.xenonx.domain.model

data class LoginResponse(
    val token: String,
    val userId: String,

)