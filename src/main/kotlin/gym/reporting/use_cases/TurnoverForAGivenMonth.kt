package gym.reporting.use_cases

import gym.subscriptions.domain.SubscriptionRepository

class TurnoverForAGivenMonth(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun handle(command: TurnoverForAGivenMonthQuery): Double {

        var turnoverForGivenMonth = 0.0

        subscriptionRepository.onGoingSubscriptions(command.asOfDate).values.forEach {
            turnoverForGivenMonth = it.monthlyTurnover()
        }

        return turnoverForGivenMonth
    }
}
