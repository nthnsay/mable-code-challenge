package org.example

import java.math.BigDecimal

class TransferService {
    fun transfer(from: Account, to: Account, amount: BigDecimal): TransferResult {
        val request = TransferRequest(from.id, to.id ,amount)
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return TransferResult(request,
                success=false, errorMessage =
                    "Amount must be positive")
        }

        if (from.id == to.id) {
            return TransferResult(request, false, "Cannot transfer to same account")
        }

        if (from.balance.compareTo(amount) < 0) {
            return TransferResult(request,false, "Insufficient funds")
        }

        from.balance = from.balance.subtract(amount)
        to.balance = to.balance.add(amount)

        return TransferResult(request, true, null)
    }
}