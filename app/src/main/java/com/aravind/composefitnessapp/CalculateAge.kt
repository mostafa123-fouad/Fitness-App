package com.aravind.composefitnessapp

import java.text.SimpleDateFormat
import java.util.*

fun calculateAge(dateOfBirth: String): Int {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val birthDate = dateFormat.parse(dateOfBirth)

    val calendar = Calendar.getInstance()
    calendar.time = birthDate

    val birthYear = calendar.get(Calendar.YEAR)
    val birthMonth = calendar.get(Calendar.MONTH)
    val birthDay = calendar.get(Calendar.DAY_OF_MONTH)

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    var age = currentYear - birthYear

    if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
        age--
    }

    return age
}
