package org.lotka.xenonx.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lotka.xenonx.domain.model.LoginResponse
import org.lotka.xenonx.domain.model.RegisterResponse
import org.lotka.xenonx.domain.repository.AuthRepository
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

class SighUpUseCase @Inject constructor(
     val authRepository: AuthRepository
) {
        companion object {
            private val PASSWORD_PATTERN = Regex("^(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}\$")
        }

    suspend operator fun invoke(userName: String, password: String,email:String): Flow<Resource<RegisterResponse>> {
            if (userName.isEmpty() || password.isEmpty() || email.isEmpty()) {
                return flow {
                    emit(Resource.Error("Please enter user name and password"))
                }
            }

            if (!PASSWORD_PATTERN.matches(password)) {
                return flow {
                    emit(Resource.Error("Password must be longer than 8 characters," +
                            " contain at least one number and one special character"))
                }
            }

            return authRepository.register(userName, password,email)
        }

    }



