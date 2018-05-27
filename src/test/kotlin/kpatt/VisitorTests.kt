package kpatt

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VisitorTests {

    @Test
    fun `should invoke correct method`() {
        val person = Person()
        val store = AppleStore()
        val market = GoogleMarket()

        store.accept(person)
        assertEquals("IPhone", person.mobilePhone)

        market.accept(person)
        assertEquals("Pixel", person.mobilePhone)
    }
}