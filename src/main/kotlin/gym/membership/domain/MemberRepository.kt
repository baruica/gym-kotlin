package gym.membership.domain

import Repository
import java.time.LocalDate

interface MemberRepository : Repository<Member> {

    fun findByEmailAddress(emailAddress: EmailAddress): Member?

    fun threeYearsAnniversaryMembers(date: LocalDate): List<Member>
}
