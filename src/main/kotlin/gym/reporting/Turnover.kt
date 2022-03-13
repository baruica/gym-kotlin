package gym.reporting

import gym.subscriptions.domain.Subscription

@JvmInline
value class Turnover internal constructor(val value: Int) {
    companion object {
        fun monthly(onGoingSubscriptions: List<Subscription>): Turnover {
            return Turnover(
                onGoingSubscriptions
                    .sumOf { subscription -> subscription.monthlyTurnover() }
            )
        }
    }
}
