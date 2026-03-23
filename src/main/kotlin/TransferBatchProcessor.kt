package org.example

import java.math.BigDecimal

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

            try {
                transferService.transfer(from, to, request.amount)
                TransferResult(request, true)
            } catch (e: IllegalArgumentException) {
                TransferResult(request, false, e.message)
            }
        }
    }

}