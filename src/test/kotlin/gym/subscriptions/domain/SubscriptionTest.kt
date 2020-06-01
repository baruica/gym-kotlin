package gym.subscriptions.domain

import gym.fifthOfJune
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
        val subscriptionWithoutDiscount = monthlySubscription(300, fifthOfJune(), isStudent = false)

        assertEquals(Price(300), subscriptionWithoutDiscount.price)
    }

    @Test
    fun `30% discount for yearly subscription`() {
        val subscriptionWithYearlyDiscount = yearlySubscription(1000, fifthOfJune(), isStudent = false)

        assertEquals(Price(700), subscriptionWithYearlyDiscount.price)
    }

    @Test
    fun `20% discount for students`() {
        val monthlySubscriptionWithStudentDiscount = monthlySubscription(100, fifthOfJune(), isStudent = true)
        assertEquals(Price(80), monthlySubscriptionWithStudentDiscount.price)

        val yearlySubscriptionWithStudentDiscount = yearlySubscription(100, fifthOfJune(), isStudent = true)
        assertEquals(Price(50), yearlySubscriptionWithStudentDiscount.price)
    }

    @Test
    fun `can be renewed`() {
        val subscription = monthlySubscription(100, fifthOfJune())

        assertFalse(subscription.willBeEndedAfter(LocalDate.parse("2018-07-04")))
        assertTrue(subscription.willBeEndedAfter(LocalDate.parse("2018-07-05")))

        subscription.renew()

        assertFalse(subscription.willBeEndedAfter(LocalDate.parse("2018-08-04")))
        assertTrue(subscription.willBeEndedAfter(LocalDate.parse("2018-08-05")))
    }

    @Test
    fun `can be ongoing`() {
        val ongoingSubscription = monthlySubscription(100, fifthOfJune())

        assertFalse(ongoingSubscription.isOngoing(LocalDate.parse("2018-06-04")))
        assertTrue(ongoingSubscription.isOngoing(LocalDate.parse("2018-06-05")))
        assertTrue(ongoingSubscription.isOngoing(LocalDate.parse("2018-06-19")))
        assertTrue(ongoingSubscription.isOngoing(LocalDate.parse("2018-07-04")))
        assertFalse(ongoingSubscription.isOngoing(LocalDate.parse("2018-07-05")))
    }

    @Test
    fun `can tell if it'll be ended as from a given date`() {
        val subscriptionEndingEndOfJune = monthlySubscription(100, fifthOfJune())

        assertFalse(subscriptionEndingEndOfJune.willBeEndedAfter(LocalDate.parse("2018-07-04")))
        assertTrue(subscriptionEndingEndOfJune.willBeEndedAfter(LocalDate.parse("2018-07-05")))
    }

    @Test
    fun `has a monthly turnover`() {
        val monthlySubscription = monthlySubscription(100, fifthOfJune())
        assertEquals(100, monthlySubscription.monthlyTurnover())

        val yearlySubscription = yearlySubscription(1200, fifthOfJune())
        assertEquals(70, yearlySubscription.monthlyTurnover())
    }
}
