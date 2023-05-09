package ru.chernov.urlshortener.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class StringRandomizer {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = UPPER + LOWER + DIGITS;


    public static String nextAlphanumeric(Integer length) {
        Random random = ThreadLocalRandom.current();
        char[] charArray = new char[length];

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC.length() - 1);
            charArray[i] = ALPHANUMERIC.charAt(randomIndex);
        }
        return new String(charArray);
    }

}
