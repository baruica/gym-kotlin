package gym.reporting

import gym.subscriptions.domain.Subscription

data class Turnover internal constructor(val total: Double) {
    companion object {
        fun monthly(onGoingSubscriptions: List<Subscription>): Turnover {
            return Turnover(
                onGoingSubscriptions
                    .sumByDouble { subscription -> subscription.monthlyTurnover() }
            )
        }
    }
}
