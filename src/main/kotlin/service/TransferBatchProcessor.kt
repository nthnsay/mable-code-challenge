package org.example.Service

import org.example.domain.Account
import org.example.domain.TransferRequest
import org.example.domain.TransferResult

class TransferBatchProcessor(private val transferService: TransferService) {
    fun process(requests: List<TransferRequest>,
                accounts: Map<String, Account>): List<TransferResult>{
        return requests.map{ request ->
            val from = accounts[request.fromAccountId]
            val to = accounts[request.toAccountId]

            if(from == null){
                return@map TransferResult(request, false, "Source account not found")
            }
            if (to == null) {
                return@map TransferResult(request, false, "Destination account not found")
            }

            transferService.transfer(from, to, request.amount)
        }
    }

}