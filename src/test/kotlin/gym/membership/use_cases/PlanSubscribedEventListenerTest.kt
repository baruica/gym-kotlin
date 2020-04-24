package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.infrastructure.MemberInMemoryRepository
import gym.subscriptions.domain.PlanSubscribed
import gym.subscriptions.domain.SubscriptionId
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class PlanSubscribedEventListenerTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val subscriptionStartDate = LocalDate.now()
        val email = EmailAddress("luke@gmail.com")

        assertNull(memberRepository.findByEmail(email))

        val tested = PlanSubscribedEventListener(memberRepository)

        val event = tested.handle(
            PlanSubscribed(
                SubscriptionId("def"),
                subscriptionStartDate,
                email.email
            )
        )

        assertTrue(memberRepository.findByEmail(email) is Member)
        assertEquals(email.email, event?.memberEmail)
    }
}
