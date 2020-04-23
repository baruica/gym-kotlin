package gym.plans.domain

inline class PlanId(val id: String)

sealed class Plan(
    open val id: PlanId,
    priceAmount: Int
) {
    internal var price: Price = Price(priceAmount)

    companion object {
        fun new(id: PlanId, price: Int, planDurationInMonths: Int): Plan {
            return when (planDurationInMonths) {
                1 -> MonthlyPlan(id, price)
                12 -> YearlyPlan(id, price)
                else -> throw IllegalArgumentException("Plan is either monthly (1 month) or yearly (12 months)")
            }
        }
    }

    data class MonthlyPlan(override val id: PlanId, val priceAmount: Int) : Plan(id, priceAmount) {
        override fun toString(): String = "Monthly plan for $price"
    }

    data class YearlyPlan(override val id: PlanId, val priceAmount: Int) : Plan(id, priceAmount) {
        override fun toString(): String = "Yearly plan for $price"
    }

    fun changePrice(newPriceAmount: Int) {
        price = Price(newPriceAmount)
    }
}

internal data class Price(val amount: Int) {
    init {
        require(amount >= 0) { "Price amount must be non-negative, was $amount" }
    }

    override fun toString(): String = "$amount euros"
}
