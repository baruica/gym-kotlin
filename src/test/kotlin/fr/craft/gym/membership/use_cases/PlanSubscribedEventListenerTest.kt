package fr.craft.gym.membership.use_cases

import fr.craft.gym.membership.domain.EmailAddress
import fr.craft.gym.membership.domain.Member
import fr.craft.gym.membership.infrastructure.MemberInMemoryRepository
import fr.craft.gym.subscriptions.domain.PlanSubscribed
import fr.craft.gym.subscriptions.domain.SubscriptionId
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class PlanSubscribedEventListenerTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val subscriptionId = SubscriptionId("def")
        val subscriptionStartDate = LocalDate.now()
        val email = EmailAddress("luke@gmail.com")

        assertNull(memberRepository.findByEmail(email))

        val tested = PlanSubscribedEventListener(memberRepository)

        val event = tested.handle(
            PlanSubscribed(subscriptionId, subscriptionStartDate, email)
        )

        assertTrue(memberRepository.findByEmail(email) is Member)
        assertEquals(email, event?.memberEmail)
    }
}
