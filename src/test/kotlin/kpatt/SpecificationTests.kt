package kpatt

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SpecificationTests {

    class StringHasLength(private val length: Int): Specification<String> {
        override fun isSatisfiedBy(candidate: String) = candidate.length == length
    }

    class StringHasEnd(private val suffix: String): Specification<String> {
        override fun isSatisfiedBy(candidate: String) = candidate.endsWith(suffix)
    }

    class StringContains(private val text: String): Specification<String> {
        override fun isSatisfiedBy(candidate: String) = candidate.contains(text)
    }

    @Test
    fun `simple spec should work`() {
        val data = "string"
        val satisfied = StringHasLength(6) isSatisfiedBy data
        assertTrue(satisfied)
    }

    @Test
    fun `'not' should work`() {
        val data = "string"
        val satisfied = !StringHasLength(7) isSatisfiedBy data
        assertTrue(satisfied)
    }

    @Test
    fun `'and' should work`() {
        val data = "string"
        val satisfied = StringHasLength(6) and StringHasEnd("ing") isSatisfiedBy data
        assertTrue(satisfied)
    }

    @Test
    fun `first part of 'and' should make false`() {
        val data = "string"
        val satisfied = StringHasLength(7) and StringHasEnd("ing") isSatisfiedBy data
        assertFalse(satisfied)
    }

    @Test
    fun `second part of 'and' should make false`() {
        val data = "string"
        val satisfied = StringHasLength(6) and StringHasEnd("ngi") isSatisfiedBy data
        assertFalse(satisfied)
    }

    @Test
    fun `first part of 'or' should make true`() {
        val data = "string"
        val satisfied = StringHasLength(6) or StringHasEnd("ngi") isSatisfiedBy data
        assertTrue(satisfied)
    }

    @Test
    fun `second part of 'or' should make true`() {
        val data = "string"
        val satisfied = StringHasLength(7) or StringHasEnd("ing") isSatisfiedBy data
        assertTrue(satisfied)
    }

    @Test
    fun `should invoke complex`() {
        val data = "string"
        val satisfied = (StringContains("ring") and StringHasLength(4)) or
                (!StringContains("ping") and StringHasEnd("ng")) isSatisfiedBy data
        assertTrue(satisfied)
    }
}