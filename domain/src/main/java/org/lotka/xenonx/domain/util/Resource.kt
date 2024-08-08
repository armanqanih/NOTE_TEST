package org.lotka.xenonx.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val field: String? = null // Optional field parameter
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, field: String? = null) : Resource<T>(data, message, field)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}
