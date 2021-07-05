package ru.netology.domain.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private static Faker fakerEn = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));

    public static String getFirstCard() {
        return "4444 4444 4444 4441";
    }

    public static String getFirstCardStatus() {
        return "APPROVED";
    }

    public static String getSecondCard() {
        return "4444 4444 4444 4442";
    }

    public static String getSecondCardStatus() {
        return "DECLINED";
    }

    public static String getRandomCard() {
        return fakerEn.business().creditCardNumber();
    }

    public static String getCardNumberHalfway() {
        return "1234 5678";
    }

    public static String getZeroCardNumber() {
        return "0000 0000 0000 0000";
    }

    public static String getCardNumberEmpty() {
        return "";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getValidMonth() {
        return "01";
    }

    public static String getInvalidMonth() {
        return "13";
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getValidYear() {
        return "24";
    }

    public static String getInvalidYear() {
        return "14";
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getValidOwnerCard() {
        return "YVAN PUPKIN";
    }

    public static String getInvalidOwnerCard() {
        return fakerRu.name().fullName();
    }

    public static String getInvalidOwnerCardNumbers() {
        return "123";
    }

    public static String getInvalidOwnerCardUppercaseLetters() {
        return "ivan pupkin";
    }

    public static String getInvalidOwnerCardSymbols() {
        return ",.!";
    }

    public static String getEmptyOwnerCard() {
        return "";
    }

    public static String getEmptyOwnerMinSymbols() {
        return "A";
    }

    public static String getEmptyOwnerCardMaxSumbols() {
        return "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
    }

    public static String getValidCvs() {
        return "123";
    }

    public static String getInvalidCvs() {
        return "1";
    }

    public static String getEmptyCvs() {
        return "";
    }

    public static String getZeroCvs() {
        return "000";
    }

}
