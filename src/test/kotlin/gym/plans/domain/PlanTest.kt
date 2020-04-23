package gym.plans.domain

import gym.plans.domain.Plan.MonthlyPlan
import gym.plans.domain.Plan.YearlyPlan
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class PlanTest {

    @Test
    fun `has a factory method to get specific type of plan`() {
        val yearlyPlan = Plan.new(PlanId("abc"), 600, 12)
        assertTrue(yearlyPlan is YearlyPlan)

        val monthlyPlan = Plan.new(PlanId("def"), 200, 1)
        assertTrue(monthlyPlan is MonthlyPlan)
    }

    @Test
    fun `a plan cannot be anything other than monthly or yearly`() {
        assertFailsWith<java.lang.IllegalArgumentException> {
            Plan.new(PlanId("abc"), 400, 4)
        }
    }

    @Test
    fun `has a valid price`() {
        assertFailsWith<IllegalArgumentException> {
            MonthlyPlan(PlanId("abc"), -10)
        }
    }

    @Test
    fun `can change its price`() {
        val tested = MonthlyPlan(PlanId("abc"), 400)
        tested.changePrice(500)

        assertEquals(Price(500), tested.price)
    }

    @Test
    fun `has a description`() {
        val yearlyPlan = YearlyPlan(PlanId("abc"), 700)
        assertEquals("Yearly plan for 700 euros", yearlyPlan.toString())

        val monthlyPlan = MonthlyPlan(PlanId("def"), 300)
        assertEquals("Monthly plan for 300 euros", monthlyPlan.toString())
    }
}
