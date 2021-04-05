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
    fun `10 percent discount for yearly subscription`() {
        val subscriptionWithYearlyDiscount = yearlySubscription(1000, fifthOfJune(), isStudent = false)

        assertEquals(Price(900), subscriptionWithYearlyDiscount.price)
    }

    @Test
    fun `20 percent discount for students`() {
        val monthlySubscriptionWithStudentDiscount = monthlySubscription(100, fifthOfJune(), isStudent = true)
        assertEquals(Price(80), monthlySubscriptionWithStudentDiscount.price)

        val yearlySubscriptionWithStudentDiscount = yearlySubscription(100, fifthOfJune(), isStudent = true)
        assertEquals(Price(72), yearlySubscriptionWithStudentDiscount.price)
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
        assertEquals(90, yearlySubscription.monthlyTurnover())
    }

    @Test
    fun `can tell if it'll have its 3 years anniversary on a given date`() {
        val threeYearsAnniversarySubscription = yearlySubscription(1000, fifthOfJune())

        assertFalse(threeYearsAnniversarySubscription.hasThreeYearsAnniversaryOn(LocalDate.parse("2021-06-04")))
        assertTrue(threeYearsAnniversarySubscription.hasThreeYearsAnniversaryOn(LocalDate.parse("2021-06-05")))
        assertFalse(threeYearsAnniversarySubscription.hasThreeYearsAnniversaryOn(LocalDate.parse("2021-06-06")))
    }

    @Test
    fun `5 percent discount after 3 years`() {
        val threeYearsOldSubscription = yearlySubscription(1000, fifthOfJune())

        assertEquals(Price(900), threeYearsOldSubscription.price)

        threeYearsOldSubscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"))

        assertEquals(Price(855), threeYearsOldSubscription.price)
    }
}
