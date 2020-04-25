package gym.membership.domain

import gym.fifthOfJune
import gym.subscriptions.domain.SubscriptionId
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MemberTest {

    @Test
    fun `is 3 years anniversary`() {
        val memberWith3yearsAnniversaryOnTheFifthOfJune = Member(
            MemberId("abc"),
            EmailAddress("julie@gmail.com"),
            SubscriptionId("def"),
            fifthOfJune().minusYears(3)
        )

        assertFalse(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-06-04")))
        assertTrue(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(fifthOfJune()))
        assertFalse(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-07-06")))
    }
}
