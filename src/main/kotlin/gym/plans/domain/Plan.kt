package gym.plans.domain

import common.Aggregate
import common.AggregateId

inline class PlanId(private val id: String) : AggregateId {
    override fun toString(): String = id
}

class Plan private constructor(
    override val id: PlanId,
    internal var price: Price,
    private val duration: Duration
) : Aggregate {
    override val raisedEvents = mutableListOf<PlanEvent>()

    companion object {
        fun new(
            id: PlanId,
            priceAmount: Int,
            durationInMonths: Int
        ): Plan {
            val plan = Plan(
                id,
                Price(priceAmount),
                Duration(durationInMonths)
            )

            plan.raisedEvents.add(
                NewPlanCreated(
                    id.toString(),
                    plan.price.amount,
                    plan.duration.durationInMonths
                )
            )

            return plan
        }
    }

    fun changePrice(newPriceAmount: Int) {
        val oldPrice = price
        price = Price(newPriceAmount)

        if (oldPrice != price) {
            raisedEvents.add(
                PlanPriceChanged(
                    id.toString(),
                    oldPrice.amount,
                    price.amount
                )
            )
        }
    }
}

internal data class Price(val amount: Int) {
    init {
        require(amount >= 0) {
            "Price amount must be non-negative, was $amount"
        }
    }
}

internal data class Duration(val durationInMonths: Int) {
    init {
        require(listOf(1, 12).contains(durationInMonths)) {
            "Plan duration is either 1 month or 12 months, was $durationInMonths"
        }
    }
}
