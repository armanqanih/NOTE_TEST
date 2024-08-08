import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.InvalidCredentialsException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import org.lotka.xenonx.domain.model.LoginResponse
import org.lotka.xenonx.domain.model.RegisterResponse
import org.lotka.xenonx.domain.repository.AuthRepository
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(userName: String, password: String): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(userName, password).await()
            val user = authResult.user
            if (user != null) {
                emit(Resource.Success(LoginResponse(token = user.uid, userId = user.uid)))
            } else {
                emit(Resource.Error("Failed to login"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Oops, something went wrong"))
        }
    }

    override suspend fun register(
        email: String,
        userName: String,
        password: String,
    ): Flow<Resource<RegisterResponse>> = flow {
        emit(Resource.Loading())
        try {
            // Register the user with email and password
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user

            if (user != null) {
                // Update the user's profile with the provided username
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(userName)
                    .build()

                user.updateProfile(profileUpdates).await()

                // Emit a success response with the user ID and displayName
                emit(Resource.Success(RegisterResponse(token = user.uid, userId = user.uid, displayName = user.displayName)))
            } else {
                emit(Resource.Error("Failed to register"))
            }
        } catch (e: FirebaseAuthException) {
            val errorMessage = when (e.errorCode) {
                "ERROR_EMAIL_ALREADY_IN_USE" -> "This email is already in use."
                "ERROR_INVALID_EMAIL" -> "Invalid email format."
                "ERROR_WEAK_PASSWORD" -> "The password is too weak."
                else -> e.message ?: "Oops, something went wrong"
            }
            emit(Resource.Error(errorMessage))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Oops, something went wrong"))
        }
    }

}
