package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    require(args.isNotEmpty()) { "Please provide a CSV file path" }

    val csvPath = args[0]
    val csvContent = args[1]
}