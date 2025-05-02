package com.aravind.composefitnessapp

import java.text.SimpleDateFormat
import java.util.*

fun calculateAge(dateOfBirth: String): String {
    if (dateOfBirth.isBlank()) {
        return "0"
    }
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val birthDate = dateFormat.parse(dateOfBirth) ?: return "0"
    val birthCalendar = Calendar.getInstance()
    birthCalendar.time = birthDate

    val today = Calendar.getInstance()

    var age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

    if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
        age--
    }

    return age.toString()
}

