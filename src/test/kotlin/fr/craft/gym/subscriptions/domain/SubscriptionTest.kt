package fr.craft.gym.subscriptions.domain

import fr.craft.gym.fifthOfJune
import fr.craft.gym.plans.domain.PlanId
import org.junit.Test
import java.time.LocalDate
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SubscriptionTest {

    @Test
    fun `base price for a monthly subscription`() {
        val subscriptionWithoutDiscount = monthlySubscription(300, fifthOfJune(), false)

        assertEquals(300, subscriptionWithoutDiscount.price)
    }

    @Test
    fun `30% discount for yearly subscription`() {
        val subscriptionWithYearlyDiscount = yearlySubscription(1000, fifthOfJune(), false)

        assertEquals(700, subscriptionWithYearlyDiscount.price)
    }

    @Test
    fun `20% discount for students`() {
        val monthlySubscriptionWithStudentDiscount = monthlySubscription(100, fifthOfJune(), true)
        assertEquals(80, monthlySubscriptionWithStudentDiscount.price)

        val yearlySubscriptionWithStudentDiscount = yearlySubscription(100, fifthOfJune(), true)
        assertEquals(50, yearlySubscriptionWithStudentDiscount.price)
    }

    @Test
    fun `can be ongoing`() {
        val ongoingSubscription = monthlySubscription(100, fifthOfJune(), false)

        val dateInJune = LocalDate.parse("2018-06-19")

        assertTrue(ongoingSubscription.isOngoing(dateInJune))
    }

    @Test
    fun `can tell if it'll be ended as from a given date`() {
        val subscriptionEndingEndOfJune = monthlySubscription(100, fifthOfJune(), false)

        assertFalse(subscriptionEndingEndOfJune.willBeEnded(LocalDate.parse("2018-07-04")))
        assertTrue(subscriptionEndingEndOfJune.willBeEnded(LocalDate.parse("2018-07-05")))
    }

    @Test
    fun `can be renewed`() {
        val subscription = monthlySubscription(100, fifthOfJune(), false)

        assertFalse(subscription.willBeEnded(LocalDate.parse("2018-07-04")))
        assertTrue(subscription.willBeEnded(LocalDate.parse("2018-07-05")))

        subscription.renew()

        assertFalse(subscription.willBeEnded(LocalDate.parse("2018-08-04")))
        assertTrue(subscription.willBeEnded(LocalDate.parse("2018-08-05")))
    }

    @Test
    fun `has a monthly turnover`() {
        val monthlySubscription = monthlySubscription(100, fifthOfJune(), false)
        assertEquals(100.0, monthlySubscription.monthlyTurnover())

        val yearlySubscription = yearlySubscription(1200, fifthOfJune(), false)
        assertEquals(70.0, yearlySubscription.monthlyTurnover())
    }

    private fun monthlySubscription(basePrice: Int, startDate: LocalDate, isStudent: Boolean): Subscription {
        return newSubscription(monthlyChosenPlan(basePrice), startDate, isStudent)
    }

    private fun yearlySubscription(basePrice: Int, startDate: LocalDate, isStudent: Boolean): Subscription {
        return newSubscription(yearlyChosenPlan(basePrice), startDate, isStudent)
    }

    private fun newSubscription(chosenPlan: ChosenPlan, startDate: LocalDate, isStudent: Boolean): Subscription {
        return Subscription(
            SubscriptionId(UUID.randomUUID().toString()),
            chosenPlan,
            startDate,
            isStudent
        )
    }

    private fun monthlyChosenPlan(basePrice: Int) = chosenPlan(basePrice, 1)

    private fun yearlyChosenPlan(basePrice: Int) = chosenPlan(basePrice, 12)

    private fun chosenPlan(price: Int, durationInMonths: Int) =
        ChosenPlan(
            PlanId(UUID.randomUUID().toString()),
            price,
            durationInMonths,
            "$durationInMonths month(s) plan for $price euros"
        )
}
