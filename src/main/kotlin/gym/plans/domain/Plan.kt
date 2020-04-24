package gym.plans.domain

inline class PlanId(private val id: String) {
    override fun toString(): String {
        return id
    }
}

sealed class Plan(val planId: PlanId, priceAmount: Int) {

    val raisedEvents: MutableList<PlanEvent> = mutableListOf()

    init {
        raisedEvents.add(
            PlanEvent.NewPlanCreated(planId.toString())
        )
    }

    internal var price: Price = Price(priceAmount)

    companion object {
        fun new(id: String, price: Int, planDurationInMonths: Int): Plan {
            return when (planDurationInMonths) {
                1 -> MonthlyPlan(id, price)
                12 -> YearlyPlan(id, price)
                else -> throw PlanException("Plan is either monthly or yearly")
            }
        }
    }

    data class MonthlyPlan(val id: String, val priceAmount: Int) : Plan(PlanId(id), priceAmount) {
        override fun toString(): String = "Monthly plan for $price"
    }

    data class YearlyPlan(val id: String, val priceAmount: Int) : Plan(PlanId(id), priceAmount) {
        override fun toString(): String = "Yearly plan for $price"
    }

    fun changePrice(newPriceAmount: Int) {
        price = Price(newPriceAmount)

        raisedEvents.add(
            PlanEvent.PlanPriceChanged(planId.toString())
        )
    }
}

data class PlanException(override val message: String) : Throwable()

internal data class Price(val amount: Int) {
    init {
        require(amount >= 0) { "Price amount must be non-negative, was $amount" }
    }

    override fun toString(): String = "$amount euros"
}
