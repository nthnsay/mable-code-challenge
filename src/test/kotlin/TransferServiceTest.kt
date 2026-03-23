import org.example.Account
import org.example.TransferService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class TransferServiceTest {

    @Test
    fun `should transfer money between accounts successfully`(){
        val from = Account("1111234522226789", 200)
        val to = Account("1111234522221234", 50)

        val service = TransferService()
        service.transfer(from, to, 50)

        assertEquals(150, from.balance)
        assertEquals(100, to.balance)
    }

    @Test
    fun `Should not transfer money between accounts when there is insufficient funds`(){
        val from = Account("1111234522221234", 200)
        val to = Account("1111234522221234", 50)

        val service = TransferService()
        service.transfer (from, to, 201)

        assertEquals(200, from.balance)
        assertEquals(50, to.balance)
    }

    @Test fun `should accept valid 16 digit account number`(){
        val account = Account("1111234522221234", 200)
        assertEquals("1111234522221234", account.id)
    }

    @Test
    fun `should reject account number shorter than 16 digits`(){
        assertThrows<IllegalArgumentException> {
            Account("111123452222123", 200)
        }
    }

    @Test
    fun `should reject account number with non numeric characters`(){
        assertThrows<IllegalArgumentException> {
            Account("111123452222123A", 200)
        }
    }

    @Test fun `should reject blank account number`(){
        assertThrows<IllegalArgumentException>  {
            Account("", 201)
        }
    }
}