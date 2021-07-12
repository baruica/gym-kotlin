package gym.subscriptions.domain

import Aggregate
import java.time.LocalDate
import java.time.Period
import kotlin.math.roundToInt

@JvmInline
value class SubscriptionId(private val value: String) {
    override fun toString(): String = value
}

class Subscription private constructor(
    val id: SubscriptionId,
    private val durationInMonths: Int,
    internal val startDate: LocalDate,
    internal var endDate: LocalDate,
    internal var price: Price,
    private var threeYearsAnniversaryDiscountApplied: Boolean,
) : Aggregate {

    override fun getId(): String = id.toString()

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
                priceAfterDiscount,
                false
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
        return (price.value / durationInMonths).roundToInt()
    }

    fun hasThreeYearsAnniversaryAfter(date: LocalDate): Boolean {
        val threeYearsAfterStartDate = startDate.plusYears(3)

        return date >= threeYearsAfterStartDate
            && threeYearsAfterStartDate == endDate
            && date >= endDate
            && !threeYearsAnniversaryDiscountApplied
    }

    fun applyThreeYearsAnniversaryDiscount(date: LocalDate) {
        val hasThreeYearsAnniversaryPassed = hasThreeYearsAnniversaryAfter(date)

        if (hasThreeYearsAnniversaryPassed) {
            val newPrice = price.applyThreeYearsAnniversaryDiscount(
                hasThreeYearsAnniversaryPassed
            )

            if (price != newPrice) {
                price = newPrice
                threeYearsAnniversaryDiscountApplied = true
            }
        }
    }
}

@JvmInline
internal value class Price private constructor(val value: Double) {
    init {
        require(value >= 0) {
            "Price amount must be non-negative, was [$value]"
        }
    }

    companion object {
        operator fun invoke(value: Number): Price {
            return Price(value.toDouble())
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
        return Price((value * (1 - rate)))
    }
}
