package org.lotka.xenonx.domain.model

data class RegisterResponse(
    val token: String,
    val userId: String,
    val displayName: String? = null
)