package gym.membership.use_cases

import common.DomainEvent
import gym.subscriptions.domain.NewSubscription

class NewSubscriptionEventListener(
    private val commandHandler: CreateNewMember
) {
    fun handle(event: NewSubscription): List<DomainEvent> {

        return commandHandler.handle(
            CreateNewMemberCommand(
                event.subscriptionId,
                event.subscriptionStartDate,
                event.email
            )
        )
    }
}
