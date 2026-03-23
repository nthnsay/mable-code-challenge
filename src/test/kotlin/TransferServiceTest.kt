import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TransferServiceTest {

    @Test
    fun `should transfer money between accounts succesfully`(){
        val from = Account("12345678", 200)
        val to = Account("11111111", 50)

        val service = TransferService()
        service.transfer(from, to, 50)

        assertEquals(150, from.balance)
        assertEquals(100, to.balance)
    }
}