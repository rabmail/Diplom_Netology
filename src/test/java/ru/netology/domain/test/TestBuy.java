package ru.netology.domain.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.domain.data.DBHelper;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.page.ServicePage;

import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestBuy {
    ServicePage servicePage = new ServicePage();

    @BeforeEach
    void shouldCleanDataBaseAndOpenWeb() {
        startMaximized = true;
        open(System.getProperty("website"));
        servicePage.buy();
        servicePage.clear();
    }

    @AfterEach
    void cleanDataBases() {
        DBHelper.cleanDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldValidByeApproved() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.expectApprovadBank();
        val expected = DataHelper.getFirstCardStatus();
        if (expected == DataHelper.getSecondCardStatus()) {
            servicePage.errorBankRefusal();
        } else {
            val actual = DBHelper.getStatusPaymentBye();
            assertEquals(expected, actual);
        }
    }

    @Test
    void shouldValidByeDeclined() {
        val cardNumber = DataHelper.getSecondCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.expectApprovadBank();
        val expected = DataHelper.getSecondCardStatus();
        if (expected == DataHelper.getSecondCardStatus()) {
            servicePage.errorBankRefusal();
        } else {
            val actual = DBHelper.getStatusPaymentBye();
            assertEquals(expected, actual);
        }
    }

    @Test
    void shouldRandomCard() {
        val cardNumber = DataHelper.getRandomCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorBankRefusal();
    }

    @Test
    void shouldCardNumberHalfway() {
        val cardNumber = DataHelper.getCardNumberHalfway();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shouldZeroCardNumber() {
        val cardNumber = DataHelper.getZeroCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorBankRefusal();
    }

    @Test
    void shouldCardNumberEmpty() {
        val cardNumber = DataHelper.getCardNumberEmpty();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shouldZeroMonth() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getZeroMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shouldInvalidMonth() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidDurationCard();
    }

    @Test
    void shouldEmptyMonth() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shouldInvalidYear() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getInvalidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorCardExpired();
    }

    @Test
    void shouldEmptyYear() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shouldInvalidOwnerCard() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getInvalidOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shouldEmptyOwnerCard() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getEmptyOwnerCard();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorFieldMandatory();
    }

    @Test
    void shouldInvalidOwnerCardNumbers() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getInvalidOwnerCardNumbers();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shoudlInvalidOwnerCardSymbols() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getInvalidOwnerCardSymbols();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shoudlInvalidOwnerCardUppercaseLetters() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getInvalidOwnerCardUppercaseLetters();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }


    @Test
    void shoulInvalidCvs() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getInvalidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shoulEmptyCvs() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getEmptyCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shoulZeroCvs() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwnerCard();
        val cvs = DataHelper.getZeroCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorInvalidFormat();
    }

    @Test
    void shoudlInvalidOwnerCardMinSymbols() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getEmptyOwnerMinSymbols();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorOwner();
    }

    @Test
    void shoudlInvalidOwnerCardMaxSymbols() {
        val cardNumber = DataHelper.getFirstCard();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getEmptyOwnerCardMaxSumbols();
        val cvs = DataHelper.getValidCvs();
        servicePage.fillFields(cardNumber, month, year, owner, cvs);
        servicePage.errorOwner();
    }

}
