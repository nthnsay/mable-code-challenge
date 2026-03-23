import org.example.Account
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class AccountTest {
    @Test fun `should accept valid 16 digit account number`(){
        val account = Account("1111234522221234", BigDecimal(200))
        assertEquals("1111234522221234", account.id)
    }

    @Test
    fun `should reject account number shorter than 16 digits`(){
        assertThrows<IllegalArgumentException> {
            Account("111123452222123", BigDecimal(200))
        }
    }

    @Test
    fun `should reject account number with non numeric characters`(){
        assertThrows<IllegalArgumentException> {
            Account("111123452222123A", BigDecimal(200))
        }
    }

    @Test fun `should reject blank account number`(){
        assertThrows<IllegalArgumentException>  {
            Account("", BigDecimal(201))
        }
    }
}