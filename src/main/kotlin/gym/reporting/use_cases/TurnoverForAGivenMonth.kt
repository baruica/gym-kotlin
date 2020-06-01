package gym.reporting.use_cases

import gym.subscriptions.domain.SubscriptionRepository

class TurnoverForAGivenMonth(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: TurnoverForAGivenMonthQuery): Int {

        return subscriptionRepository.onGoingSubscriptions(command.asOfDate)
            .map { it.monthlyTurnover() }
            .fold(0, { sum, monthlyTurnover -> sum + monthlyTurnover })
    }
}
