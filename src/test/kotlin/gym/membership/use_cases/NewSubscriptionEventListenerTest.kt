package gym.membership.use_cases

import gym.Event
import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.infrastructure.MemberInMemoryRepository
import org.junit.Test
import kotlin.test.assertEquals
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
            Event.NewSubscription(
                "subscriptionId def",
                "2018-06-05",
                email.email
            )
        )

        val member = memberRepository.findByEmail(email)
        assertTrue(member is Member)
        assertEquals(member.memberId.toString(), events.last().aggregateId)
    }
}
