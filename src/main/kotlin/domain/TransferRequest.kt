package org.example.domain

import java.math.BigDecimal

data class TransferRequest(
    val fromAccountId: String,
    val toAccountId: String,
    val amount: BigDecimal

)