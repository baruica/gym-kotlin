package gym.subscriptions.domain

import gym.monthlySubscription
import gym.yearlySubscription
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SubscriptionTest {

    @Test
    fun `base price for a monthly subscription`() {
        val subscriptionWithoutDiscount = monthlySubscription(300, LocalDate.parse("2018-06-05"), isStudent = false)

        assertEquals(Price(300), subscriptionWithoutDiscount.price)
    }

    @Test
    fun `10 percent discount for yearly subscription`() {
        val subscriptionWithYearlyDiscount = yearlySubscription(1000, LocalDate.parse("2018-06-05"), isStudent = false)

        assertEquals(Price(900), subscriptionWithYearlyDiscount.price)
    }

    @Test
    fun `20 percent discount for students`() {
        val monthlySubscriptionWithStudentDiscount = monthlySubscription(100, LocalDate.parse("2018-06-05"), isStudent = true)
        assertEquals(Price(80), monthlySubscriptionWithStudentDiscount.price)

        val yearlySubscriptionWithStudentDiscount = yearlySubscription(100, LocalDate.parse("2018-06-05"), isStudent = true)
        assertEquals(Price(72), yearlySubscriptionWithStudentDiscount.price)
    }

    @Test
    fun `5 percent discount after 3 years`() {
        val subscription = yearlySubscription(1000, LocalDate.parse("2018-06-05"))
        assertEquals(Price(900), subscription.price)

        subscription.renew()
        assertEquals(Price(900), subscription.price)

        subscription.renew()
        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))
        assertEquals(Price(855), subscription.price)
    }

    @Test
    fun `3 years anniversary discount can only be applied once`() {
        val subscription = yearlySubscription(1000, LocalDate.parse("2018-06-05"))

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))
        assertEquals(Price(900), subscription.price)

        subscription.renew()
        subscription.renew()
        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))
        assertEquals(Price(855), subscription.price)

        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))
        assertEquals(Price(855), subscription.price)
    }

    @Test
    fun `can be renewed`() {
        val subscription = monthlySubscription(100, LocalDate.parse("2018-06-05"))

        assertFalse(subscription.willBeEndedAsFrom(LocalDate.parse("2018-07-05")))
        assertTrue(subscription.willBeEndedAsFrom(LocalDate.parse("2018-07-06")))

        subscription.renew()

        assertFalse(subscription.willBeEndedAsFrom(LocalDate.parse("2018-08-05")))
        assertTrue(subscription.willBeEndedAsFrom(LocalDate.parse("2018-08-06")))
    }

    @Test
    fun `can be ongoing`() {
        val ongoingSubscription = monthlySubscription(100, LocalDate.parse("2018-06-05"))

        assertFalse(ongoingSubscription.isOngoing(LocalDate.parse("2018-06-04")))
        assertTrue(ongoingSubscription.isOngoing(LocalDate.parse("2018-06-05")))
        assertTrue(ongoingSubscription.isOngoing(LocalDate.parse("2018-06-19")))
        assertTrue(ongoingSubscription.isOngoing(LocalDate.parse("2018-07-05")))
        assertFalse(ongoingSubscription.isOngoing(LocalDate.parse("2018-07-06")))
    }

    @Test
    fun `can tell if it'll be ended as from a given date`() {
        val subscriptionEndingEndOfJune = monthlySubscription(100, LocalDate.parse("2018-06-05"))

        assertFalse(subscriptionEndingEndOfJune.willBeEndedAsFrom(LocalDate.parse("2018-07-05")))
        assertTrue(subscriptionEndingEndOfJune.willBeEndedAsFrom(LocalDate.parse("2018-07-06")))
    }

    @Test
    fun `has a monthly turnover`() {
        val monthlySubscription = monthlySubscription(100, LocalDate.parse("2018-06-05"))
        assertEquals(100, monthlySubscription.monthlyTurnover())

        val yearlySubscription = yearlySubscription(1200, LocalDate.parse("2018-06-05"))
        assertEquals(90, yearlySubscription.monthlyTurnover())
    }
}
