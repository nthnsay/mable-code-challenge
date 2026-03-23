package org.example

import java.math.BigDecimal

class Account(val id: String, var balance: BigDecimal) {
    init {
        require(id.length == 16){"Account number must be 16 digits"}
        require(id.all {it.isDigit()} ){"Account number must be numeric"}
        require(id.isNotBlank()) { "Account id must not be blank" }
    }
}