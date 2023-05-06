package ru.chernov.urlshortener.utils

import java.util.*
import java.util.concurrent.ThreadLocalRandom


private const val UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
private const val LOWER = "abcdefghijklmnopqrstuvwxyz"
private const val DIGITS = "0123456789"
private const val ALPHANUMERIC = "$UPPER$LOWER$DIGITS"


fun nextAlphanumeric(length: Int): String {
    val random: Random = ThreadLocalRandom.current()
    val charArray = CharArray(length)

    for (i in 0 until length) {
        val randomIndex: Int = random.nextInt(ALPHANUMERIC.length - 1)
        charArray[i] = ALPHANUMERIC[randomIndex]
    }
    return String(charArray)
}
