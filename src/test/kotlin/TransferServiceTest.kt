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

}