package fr.craft.gym.membership.use_cases

import fr.craft.gym.membership.domain.Member
import fr.craft.gym.membership.domain.MemberId
import fr.craft.gym.membership.domain.NewMemberSubscribed
import fr.craft.gym.membership.infrastructure.InMemoryMailer
import fr.craft.gym.membership.infrastructure.MemberInMemoryRepository
import fr.craft.gym.subscriptions.domain.SubscriptionId
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SendWelcomeEmailToNewMemberTest {

    @Test
    fun handle() {

        val memberId = MemberId("abc")
        val member = Member(
            memberId,
            "bob@gmail.com",
            SubscriptionId("def"),
            LocalDate.now()
        )
        val memberRepository = MemberInMemoryRepository()
        memberRepository.store(member)

        val mailer = InMemoryMailer()

        val tested = SendWelcomeEmailToNewMember(memberRepository, mailer)

        val event = tested.handle(
            NewMemberSubscribed(memberId, member.email)
        )

        assertEquals(memberId, event.memberId)
        assertTrue(mailer.sentEmails.containsValue("Thank you for subscribing bob@gmail.com !"))
    }
}
