package fr.craft.gym.membership.use_cases

import fr.craft.gym.XYearsBeforeThe
import fr.craft.gym.fifthOfJune
import fr.craft.gym.membership.domain.EmailAddress
import fr.craft.gym.membership.domain.Member
import fr.craft.gym.membership.domain.MemberId
import fr.craft.gym.membership.infrastructure.InMemoryMailer
import fr.craft.gym.membership.infrastructure.MemberInMemoryRepository
import fr.craft.gym.subscriptions.domain.SubscriptionId
import org.junit.Test
import java.time.LocalDate
import java.util.UUID
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Send3YearsAnniversaryThankYouEmailsTest {

    @Test
    fun handle() {
        val memberRepository = MemberInMemoryRepository()

        val memberJulie = buildMember(EmailAddress("julie@gmail.com"), XYearsBeforeThe(3, fifthOfJune()))
        memberRepository.store(memberJulie)

        val memberBob = buildMember(EmailAddress("bob@gmail.com"), XYearsBeforeThe(2, fifthOfJune()))
        memberRepository.store(memberBob)

        val memberLuke = buildMember(EmailAddress("luke@gmail.com"), XYearsBeforeThe(3, fifthOfJune()))
        memberRepository.store(memberLuke)

        val mailer = InMemoryMailer()

        val tested = Send3YearsAnniversaryThankYouEmails(memberRepository, mailer)

        val event = tested.handle(
            Send3YearsAnniversaryThankYouEmailsCommand(fifthOfJune())
        )

        assertTrue(mailer.sentEmails.containsValue("Thank you for your loyalty julie@gmail.com !"))
        assertFalse(mailer.sentEmails.containsValue("Thank you for your loyalty bob@gmail.com !"))
        assertTrue(mailer.sentEmails.containsValue("Thank you for your loyalty luke@gmail.com !"))
        assertTrue(event.memberIds.contains(memberJulie.id))
        assertFalse(event.memberIds.contains(memberBob.id))
        assertTrue(event.memberIds.contains(memberLuke.id))
    }

    private fun buildMember(email: EmailAddress, startDate: LocalDate): Member = Member(
        MemberId(UUID.randomUUID().toString()),
        email,
        SubscriptionId("def"),
        startDate
    )
}
