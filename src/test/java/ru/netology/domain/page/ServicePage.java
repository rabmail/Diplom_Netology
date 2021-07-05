package ru.netology.domain.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ServicePage {
    private SelenideElement buttonBuy = $("button:nth-child(3)");
    private SelenideElement buttonBuyCredit = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(0);
    private SelenideElement buttonResume = $$("button").find(exactText("Продолжить"));
    private SelenideElement fieldCardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement fieldMonth = $("[placeholder='08']");
    private SelenideElement fieldYear = $("[placeholder='22']");
    private SelenideElement fieldOwner = $$("[class='input__control']").get(3);
    private SelenideElement fieldCvc = $("[placeholder='999']");
    private SelenideElement errorInvalidFormat = $(withText("Неверный формат"));
    private SelenideElement errorFieldMandatory = $(withText("Поле обязательно для заполнения"));
    private SelenideElement approvedBank = $(withText("Операция одобрена Банком."));
    private SelenideElement errorBankRefusal = $(withText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement errorInvalidDurationCard = $(withText("Неверно указан срок действия карты"));
    private SelenideElement errorCardExpired = $(withText("Истёк срок действия карты"));
    private SelenideElement errorOwner = $(withText("Неверно указан владелец карты"));
    private static String correctNameTitle = "AQA: Путешествие дня";
    private SelenideElement errorNameSity = $("#root > div > div > div > div:nth-child(2) > h3");
    private static String correctNameSity = "Марракеш";

    public String getСorrectNameTitle() {
        return correctNameTitle;
    }

    public String getСorrectNameCity() {
        return correctNameSity;
    }

    public String getErrorNameSity() {
        return errorNameSity.getText();
    }

    public ServicePage buy() {
        buttonBuy.click();
        return new ServicePage();
    }

    public ServicePage buyCredit() {
        buttonBuyCredit.click();
        return new ServicePage();
    }

    public ServicePage clear() {
        clearField();
        return new ServicePage();
    }

    public void clearField() {
        fieldCardNumber.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fieldMonth.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fieldYear.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fieldOwner.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fieldCvc.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    public void fillFields(String cardNumber, String month, String year, String owner, String cvs) {
        fieldCardNumber.setValue(cardNumber);
        fieldMonth.setValue(month);
        fieldYear.setValue(year);
        fieldOwner.setValue(owner);
        fieldCvc.setValue(cvs);
        buttonResume.click();
    }

    public void expectApprovadBank() {
        approvedBank.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorBankRefusal() {
        errorBankRefusal.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorOwner() {
        errorOwner.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorInvalidFormat() {
        errorInvalidFormat.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorInvalidDurationCard() {
        errorInvalidDurationCard.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorCardExpired() {
        errorCardExpired.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorFieldMandatory() {
        errorFieldMandatory.shouldBe(visible, Duration.ofSeconds(15));
    }

}

