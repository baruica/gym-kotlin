package fr.craft.gym

import java.time.LocalDate

fun fifthOfJune(): LocalDate = LocalDate.parse("2018-06-05")

fun XYearsBeforeThe(nbYearsBefore: Int, date: LocalDate): LocalDate {
    return date.minusYears(nbYearsBefore.toLong())
}
