package gym.subscriptions.domain

import common.Aggregate
import common.AggregateId
import java.time.LocalDate
import java.time.Period
import kotlin.math.roundToInt

inline class SubscriptionId(private val id: String) : AggregateId {
    override fun toString(): String = id
}

class Subscription private constructor(
    override val id: SubscriptionId,
    private val durationInMonths: Int,
    internal val startDate: LocalDate,
    internal var endDate: LocalDate,
    internal var price: Price,
) : Aggregate {
    private var threeYearsAnniversaryDiscountApplied: Boolean = false

    companion object {
        fun subscribe(
            subscriptionId: String,
            planDurationInMonths: Int,
            startDate: LocalDate,
            planPrice: Int,
            isStudent: Boolean
        ): Subscription {
            val priceAfterDiscount = Price(planPrice)
                .applyDurationDiscount(planDurationInMonths)
                .applyStudentDiscount(isStudent)

            val endDate = startDate.plusMonths(planDurationInMonths.toLong())

            return Subscription(
                SubscriptionId(subscriptionId),
                planDurationInMonths,
                startDate,
                endDate,
                priceAfterDiscount
            )
        }
    }

    fun renew() {
        endDate = endDate.plus(Period.ofMonths(durationInMonths))
    }

    fun willBeEndedAsFrom(date: LocalDate): Boolean {
        return date.isAfter(endDate)
    }

    fun isOngoing(date: LocalDate): Boolean {
        return date in startDate..endDate
    }

    fun isMonthly(): Boolean {
        return durationInMonths == 1
    }

    fun monthlyTurnover(): Int {
        return (price.amount / durationInMonths).roundToInt()
    }

    fun hasThreeYearsAnniversaryOn(date: LocalDate): Boolean {
        return date == startDate.plusYears(3)
            && date == endDate
    }

    fun applyThreeYearsAnniversaryDiscount(date: LocalDate) {
        if (!threeYearsAnniversaryDiscountApplied) {
            val newPrice = price.applyThreeYearsAnniversaryDiscount(
                hasThreeYearsAnniversaryOn(date)
            )

            if (price != newPrice) {
                price = newPrice
                threeYearsAnniversaryDiscountApplied = true
            }
        }
    }
}

internal data class Price(val amount: Double) {
    constructor(amount: Int) : this(amount.toDouble())

    init {
        require(amount >= 0) {
            "Price amount must be non-negative, was [$amount]"
        }
    }

    internal fun applyDurationDiscount(durationInMonths: Int): Price {
        return if (durationInMonths == 12) {
            applyDiscount(0.1)
        } else this
    }

    internal fun applyStudentDiscount(isStudent: Boolean): Price {
        return if (isStudent) {
            applyDiscount(0.2)
        } else this
    }

    fun applyThreeYearsAnniversaryDiscount(hasThreeYearsAnniversary: Boolean): Price {
        return if (hasThreeYearsAnniversary) {
            applyDiscount(0.05)
        } else this
    }

    private fun applyDiscount(rate: Double): Price {
        return Price((amount * (1 - rate)))
    }
}
