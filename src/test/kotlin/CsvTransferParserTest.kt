import org.example.CsvTransferParser
import org.example.TransferRequest
import org.junit.jupiter.api.Test
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
}