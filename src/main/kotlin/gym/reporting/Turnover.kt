package gym.reporting

import gym.subscriptions.domain.Subscription

data class Turnover internal constructor(val total: Int) {
    companion object {
        fun monthly(onGoingSubscriptions: List<Subscription>): Turnover {
            return Turnover(
                onGoingSubscriptions
                    .map { it.monthlyTurnover() }
                    .fold(0, { sum, monthlyTurnover -> sum + monthlyTurnover })
            )
        }
    }
}
