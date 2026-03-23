package org.example

import java.math.BigDecimal

class  CsvTransferParser {
    fun parse(csv: String): List<TransferRequest> {
        return csv
            .lineSequence()
            .filter { it.isNotBlank() }
            .drop(1) // if there is a header row
            .map { parseRow(it) }
            .toList()
    }
    fun parseRow(row: String): TransferRequest {
        val parts = row.split(",")

        require(parts.size == 3) { "Expected 3 fields, got ${parts.size}" }

        val from = parts[0].trim()
        val to = parts[1].trim()
        val amount = parts[2].trim().toBigDecimalOrNull()?: throw IllegalArgumentException("Invalid amount")
        return TransferRequest(from, to, amount)
    }
}