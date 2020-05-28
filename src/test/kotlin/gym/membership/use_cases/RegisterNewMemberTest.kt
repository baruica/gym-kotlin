package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.domain.NewMemberRegistered
import gym.membership.infrastructure.MemberInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RegisterNewMemberTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val email = "luke@gmail.com"

        assertNull(memberRepository.findByEmailAddress(EmailAddress(email)))

        val subscriptionId = "subscriptionId def"
        val subscriptionStartDate = "2018-06-05"
        val newSubscriptionEvent = RegisterNewMemberCommand(
            subscriptionId,
            subscriptionStartDate,
            email
        )

        val tested = RegisterNewMember(memberRepository)
        val events = tested.handle(
            newSubscriptionEvent
        )

        assertEquals(
            events.last(),
            NewMemberRegistered(
                events.last().aggregateId(),
                email,
                subscriptionId,
                subscriptionStartDate
            )
        )
    }
}
