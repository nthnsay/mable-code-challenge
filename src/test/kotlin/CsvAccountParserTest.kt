import org.example.CsvParser.CsvAccountParser
import org.example.CsvParser.CsvTransferParser
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class CsvAccountParserTest {
    @Test
    fun `should parse vaild account row`(){
        val parser = CsvAccountParser()
        val row = "1111234522226789,5000.00"

        val account = parser.parseRow(row)

        assertEquals("1111234522226789", account.id)
        assertEquals(BigDecimal("5000.00"), account.balance)
    }

    @Test
    fun `should parse multiple account rows`(){
        val parser = CsvAccountParser()
        val csv = """
            1234567811111111, 500.00
            1234567822222222, 20000.00
        """.trimIndent()

        val accounts = parser.parse(csv)

        assertEquals(2, accounts.size)
        assertEquals(BigDecimal("500.00"), accounts["1234567811111111"]?.balance)
    }
}