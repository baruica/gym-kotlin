package gym.membership.use_cases

import common.DomainEvent
import gym.membership.domain.NewMembership

class NewMembershipEventListener(
    private val commandHandler: SendWelcomeEmailToNewMember
) {
    fun handle(event: NewMembership): List<DomainEvent> {

        return commandHandler.handle(
            SendWelcomeEmailToNewMemberCommand(
                event.memberId
            )
        )
    }
}
