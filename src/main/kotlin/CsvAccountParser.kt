package org.example

class CsvAccountParser {

    fun parse(csv: String): Map<String, Account> {
        return csv
            .lineSequence()
            .filter { it.isNotBlank() }
            .map { parseRow(it) }
            .associateBy { it.id }
    }

    fun parseRow(row: String): Account {
        val parts = row.split(",")
        require(parts.size == 2) { "Invalid account row" }

        val id = parts[0].trim()
        val balance = parts[1].trim().toBigDecimalOrNull()
            ?: throw IllegalArgumentException("Invalid balance")

        return Account(id, balance)
    }
}