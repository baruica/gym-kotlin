package fr.craft.gym.subscriptions.domain

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class PeriodTest {

    @Test
    fun `can be before a date`() {
        val tested = Period(LocalDate.parse("2018-06-01"), 2)

        assertFalse(tested.isBefore(LocalDate.parse("2018-07-31")))
        assertTrue(tested.isBefore(LocalDate.parse("2018-08-01")))
    }

    @Test
    fun `contains a date`() {
        val tested = Period(LocalDate.parse("2018-06-01"), 2)

        assertTrue(tested.contains(LocalDate.parse("2018-06-01")))
        assertTrue(tested.contains(LocalDate.parse("2018-06-02")))

        assertTrue(tested.contains(LocalDate.parse("2018-07-31")))
        assertFalse(tested.contains(LocalDate.parse("2018-08-01")))
    }

    @Test
    operator fun next() {
        val tested = Period(LocalDate.parse("2018-06-01"), 2)

        assertTrue(tested.next().contains(LocalDate.parse("2018-09-30")))
        assertFalse(tested.next().contains(LocalDate.parse("2018-10-01")))
    }
}
