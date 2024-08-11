package org.lotka.xenonx.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lotka.xenonx.domain.repository.AuthRepository
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {


    suspend operator fun invoke(userName: String, password: String): Flow<Resource<LoginResponse>> {
        return authRepository.login(userName, password)
    }
}
