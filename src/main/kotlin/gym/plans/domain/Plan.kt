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
                else -> throw PlanException("Plan is either monthly or yearly")
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

data class PlanException(override val message: String) : Throwable()

internal data class Price(val amount: Int) {
    init {
        require(amount >= 0) { "Price amount must be non-negative, was $amount" }
    }

    override fun toString(): String = "$amount euros"
}
