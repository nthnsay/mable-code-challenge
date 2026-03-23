package org.example

class TransferService {
    fun transfer(from: Account, to: Account, amount: Int) {
        from.balance -= amount
        to.balance += amount
    }
}