package org.example

import java.math.BigDecimal

class TransferService {
    fun transfer(from: Account, to: Account, amount: BigDecimal) {
       val fromTransferBalance = from.balance - amount
       if( fromTransferBalance <= BigDecimal.ZERO) {
           return
       }else{
           from.balance -= amount
           to.balance += amount
       }
    }
}