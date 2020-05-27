package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.domain.NewMembership
import gym.membership.infrastructure.MemberInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CreateNewMemberTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val email = "luke@gmail.com"

        assertNull(memberRepository.findByEmailAddress(EmailAddress(email)))

        val subscriptionId = "subscriptionId def"
        val subscriptionStartDate = "2018-06-05"
        val newSubscriptionEvent = CreateNewMemberCommand(
            subscriptionId,
            subscriptionStartDate,
            email
        )

        val tested = CreateNewMember(memberRepository)
        val events = tested.handle(
            newSubscriptionEvent
        )

        assertEquals(
            events.last(),
            NewMembership(
                events.last().aggregateId(),
                email,
                subscriptionId,
                subscriptionStartDate
            )
        )
    }
}
