package gym.membership.domain

import common.Repository
import java.time.LocalDate

interface MemberRepository : Repository {

    fun findByEmailAddress(emailAddress: EmailAddress): Member?

    fun threeYearsAnniversaryMembers(asOfDate: LocalDate): List<Member>
}
