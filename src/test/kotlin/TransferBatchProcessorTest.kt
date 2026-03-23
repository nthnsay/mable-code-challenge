import org.example.domain.Account
import org.example.Service.TransferBatchProcessor
import org.example.domain.TransferRequest
import org.example.Service.TransferService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TransferBatchProcessorTest {

    @Test
    fun `should process multiple transfers and return mixed results`() {
        val accounts = mutableMapOf(
            "1234567890123456" to Account("1234567890123456", BigDecimal("1000.00")),
            "9999999999999999" to Account("9999999999999999", BigDecimal("500.00"))
        )

        val requests = listOf(
            TransferRequest("1234567890123456", "9999999999999999", BigDecimal("200.00")),
            TransferRequest("1234567890123456", "9999999999999999", BigDecimal("900.00"))
        )

        val transferService = TransferService()
        val processor = TransferBatchProcessor(transferService)

        val results = processor.process(requests, accounts)

        assertEquals(2, results.size)
        assertTrue(results[0].success)
        assertFalse(results[1].success)
        assertEquals("Insufficient funds", results[1].errorMessage)

        assertEquals(BigDecimal("800.00"), accounts["1234567890123456"]?.balance)
        assertEquals(BigDecimal("700.00"), accounts["9999999999999999"]?.balance)
    }
}