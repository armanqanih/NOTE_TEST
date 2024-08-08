package org.lotka.xenonx.domain.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.LoginResponse
import org.lotka.xenonx.domain.model.RegisterResponse
import org.lotka.xenonx.domain.util.Resource

interface AuthRepository {

    suspend fun login(userName: String, password: String): Flow<Resource<LoginResponse>>
    suspend fun register(email: String,userName: String, password: String): Flow<Resource<RegisterResponse>>

}