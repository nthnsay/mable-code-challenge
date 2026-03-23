import org.example.CsvTransferParser
import org.example.TransferRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import kotlin.test.assertEquals

class CsvTransferParserTest {

    @Test
    fun `should parse valid transfer row`(){
        val parser = CsvTransferParser()

        val row = "1111234522226789,1212343433335665,500.00"

        val result = parser.parseRow(row)

        assertEquals(
            TransferRequest(
                fromAccountId = "1111234522226789",
                toAccountId = "1212343433335665",
                amount = BigDecimal("500.00")
            ),
            result
        )

    }

    @Test
    fun `should reject row with missing fields`() {
        val parser = CsvTransferParser()

        val row = "1234567890123456,9999999999999999"

        assertThrows<IllegalArgumentException> {
            parser.parseRow(row)
        }
    }

    @Test
    fun `should reject row with non numeric amount`() {
        val parser = CsvTransferParser()

        val row = "1234567890123456,9999999999999999,abc"

        assertThrows<IllegalArgumentException> {
            parser.parseRow(row)
        }
    }

    @Test
    fun `should parse multiple rows`() {
        val parser = CsvTransferParser()

        val csv = """
            fromAccountId,toAccountId,amount
            1234567811111111,1234567822222222,300.00
            1111111187654321,2222222287654321, 50.00
        """.trimIndent()

        val results = parser.parse(csv)

        assertEquals(2, results.size)
        assertEquals(TransferRequest("1234567811111111", "1234567822222222", BigDecimal("300.00")), results[0])
        assertEquals(TransferRequest("1111111187654321", "2222222287654321", BigDecimal("50.00")), results[1])
    }

    @Test
    fun `should parse vaild account row`(){
        val parser = CsvTransferParser()
        val row = "1111234522226789,5000.00"

        val account = parser.parseAccountRow(row)

        assertEquals("1111234522226789", account.id)
        assertEquals(BigDecimal("500.00"), account.balance)
    }
}