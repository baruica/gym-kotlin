package gym.membership.domain

import common.Repository
import java.time.LocalDate

interface MemberRepository : Repository {

    fun findByEmail(email: Email): Member?

    fun threeYearsAnniversaryMembers(asOfDate: LocalDate): Map<MemberId, Member>
}
