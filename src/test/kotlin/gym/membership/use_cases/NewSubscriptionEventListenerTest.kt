package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberEvent
import gym.membership.infrastructure.MemberInMemoryRepository
import gym.subscriptions.domain.SubscriptionEvent
import org.junit.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class NewSubscriptionEventListenerTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val email = EmailAddress("luke@gmail.com")

        assertNull(memberRepository.findByEmail(email))

        val tested = NewSubscriptionEventListener(memberRepository)

        val events = tested.handle(
            SubscriptionEvent.NewSubscription(
                "subscriptionId def",
                "2018-06-05",
                email.email
            )
        )

        val member = memberRepository.findByEmail(email)
        assertTrue(member is Member)
        assertTrue(events.last() is MemberEvent.NewMemberSubscribed)
    }
}
