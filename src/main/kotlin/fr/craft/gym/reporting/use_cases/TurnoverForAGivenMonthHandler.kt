package fr.craft.gym.reporting.use_cases

import fr.craft.gym.subscribing.domain.SubscriptionRepository

class TurnoverForAGivenMonthHandler(private val subscriptionRepository: SubscriptionRepository) {

    fun handle(command: TurnoverForAGivenMonth): Double {

        var turnoverForAGivenMonth = 0.0

        subscriptionRepository.onGoingSubscriptions(command.asOfDate).forEach {
            turnoverForAGivenMonth = it.value.monthlyTurnover()
        }

        return turnoverForAGivenMonth
    }
}
