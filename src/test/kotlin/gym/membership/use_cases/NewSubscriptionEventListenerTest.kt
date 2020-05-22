package gym.membership.use_cases

import gym.membership.domain.Email
import gym.membership.domain.NewMemberSubscribed
import gym.membership.infrastructure.MemberInMemoryRepository
import gym.subscriptions.domain.NewSubscription
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class NewSubscriptionEventListenerTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val email = "luke@gmail.com"

        assertNull(memberRepository.findByEmail(Email(email)))

        val subscriptionId = "subscriptionId def"
        val subscriptionStartDate = "2018-06-05"
        val newSubscriptionEvent = NewSubscription(
            subscriptionId,
            subscriptionStartDate,
            email
        )

        val tested = NewSubscriptionEventListener(memberRepository)
        val events = tested.handle(
            newSubscriptionEvent
        )

        assertEquals(
            events.last(),
            NewMemberSubscribed(
                events.last().aggregateId(),
                email,
                subscriptionId,
                subscriptionStartDate
            )
        )
    }
}
