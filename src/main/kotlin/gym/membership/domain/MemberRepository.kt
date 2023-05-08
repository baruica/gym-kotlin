package gym.membership.domain

import Repository
import java.time.LocalDate

interface MemberRepository : Repository<String, Member> {

    fun findByEmailAddress(emailAddress: EmailAddress): Member?

    fun threeYearsAnniversaryMembers(date: LocalDate): List<Member>
}
