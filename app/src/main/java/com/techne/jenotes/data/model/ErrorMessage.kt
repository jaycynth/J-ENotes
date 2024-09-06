package com.techne.jenotes.data.model

data class ErrorMessage(
    val statusCode: Int? = null,
    val message: Any? = null,
    val error: String? = null
) {
    fun getFormattedMessage(): String {
        return when (message) {
            is String -> message
            is List<*> -> message.joinToString(", ") { it.toString() }
            else -> "Unknown error"
        }
    }
}
