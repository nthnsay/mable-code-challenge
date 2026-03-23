package org.example

class TransferService {
    fun transfer(from: Account, to: Account, amount: Int) {
       val fromTransferBalance = from.balance - amount
       if( fromTransferBalance <=0){
           return
       }else{
           from.balance -= amount
           to.balance += amount
       }
    }
}