package org.example


import org.example.CsvParser.CsvAccountParser
import org.example.CsvParser.CsvTransferParser
import org.example.Service.TransferBatchProcessor
import org.example.Service.TransferService
import java.io.File

fun main(args: Array<String>) {
    if (args.size != 2){
        println("Usage: ./gradle run --args=\"mable_account_balances.csv mable_transactions.csv\"")
        return
    }

    val accountsFilPath = args[0]
    val transfersFilePath = args[1]

    val accountsCsv = try{
        File(accountsFilPath).readText()
    }catch(e: Exception){
        println("Unable to read accounts from file: ${e.message}")

        return
    }


    val transfersCsv = try {
        File(transfersFilePath).readText()
    } catch (e: Exception) {
        println("Failed to read transfers file: ${e.message}")
        return
    }

    val accounts = try {
        CsvAccountParser().parse(accountsCsv).toMutableMap()
    } catch (e: Exception) {
        println("Failed to parse accounts CSV: ${e.message}")
        return
    }

    val requests = try {
        CsvTransferParser().parse(transfersCsv)
    } catch (e: Exception) {
        println("Failed to parse transfers CSV: ${e.message}")
        return
    }

    val processor = TransferBatchProcessor(TransferService())
    val results = processor.process(requests, accounts)

    val successCount = results.count { it.success }
    val failureCount = results.count { !it.success }

    println("Processed ${results.size} transfers")
    println("Successful: $successCount")
    println("Failed: $failureCount")

    if (failureCount > 0) {
        println()
        println("Failures:")
        results
            .filter { !it.success }
            .forEach { result ->
                println(
                    "${result.request.fromAccountId},${result.request.toAccountId},${result.request.amount} -> ${result.errorMessage}"
                )
            }
    }

    println()
    println("Final account balances:")
    accounts.values
        .sortedBy { it.id }
        .forEach { account ->
            println("${account.id},${account.balance}")
        }
}