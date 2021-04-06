package gym.membership.domain

import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MemberTest {

    @Test
    fun `is 3 years anniversary`() {
        val memberWith3yearsAnniversaryOnTheFifthOfJune = Member.register(
            "member abc",
            EmailAddress("julie@gmail.com"),
            LocalDate.parse("2018-06-05").minusYears(3)
        )

        assertFalse(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-06-04")))
        assertTrue(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-06-05")))
        assertFalse(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-07-06")))
    }
}
