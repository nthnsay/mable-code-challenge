import org.example.Account
import org.example.TransferService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal


class TransferServiceTest {

    @Test
    fun `should transfer money between accounts successfully`(){
        val from = Account("1111234522226789", BigDecimal(200))
        val to = Account("1111234522221234", BigDecimal(50))

        val service = TransferService()
        service.transfer(from, to, BigDecimal(50))

        assertEquals(BigDecimal(150), from.balance)
        assertEquals(BigDecimal(100), to.balance)
    }

    @Test
    fun `Should not transfer money between accounts when there is insufficient funds`(){
        val from = Account("1111234522221234", BigDecimal(200))
        val to = Account("1111234522221234", BigDecimal(50))

        val service = TransferService()
        service.transfer (from, to, BigDecimal(201))

        assertEquals(BigDecimal(200), from.balance)
        assertEquals(BigDecimal(50), to.balance)
    }

}