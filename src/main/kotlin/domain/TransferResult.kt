package org.example.domain

data class TransferResult(
    val request: TransferRequest,
    val success: Boolean,
    val errorMessage: String? = null
)