package gym.membership.use_cases

import common.DomainEvent
import gym.subscriptions.domain.NewSubscription

class NewSubscriptionEventListener(
    private val commandHandler: RegisterNewMember
) {
    fun handle(event: NewSubscription): List<DomainEvent> {

        return commandHandler.handle(
            RegisterNewMemberCommand(
                event.subscriptionId,
                event.subscriptionStartDate,
                event.email
            )
        )
    }
}
