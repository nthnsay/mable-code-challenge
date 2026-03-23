import org.example.CsvTransferParser
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class CsvAccountTransferParserTest {
    @Test
    fun `should parse vaild account row`(){
        val parser = CsvTransferParser()
        val row = "1111234522226789,5000.00"

        val account = parser.parseAccountRow(row)

        assertEquals("1111234522226789", account.id)
        assertEquals(BigDecimal("500.00"), account.balance)
    }
}