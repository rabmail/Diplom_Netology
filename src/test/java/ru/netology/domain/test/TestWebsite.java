package ru.netology.domain.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.domain.page.ServisPage;

import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebsite extends WebDriveHelper {

    ServisPage servisPage = new ServisPage();

    @BeforeEach
    void shouldCleanDataBaseAndOpenWeb() {
        startMaximized = true;
        open(System.getProperty("website"));
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
    void shouldValidPageName() {
        String nameTitle = getWebDriver().getTitle();
        assertEquals(servisPage.getСorrectNameTitle(), nameTitle);

    }

    @Test
    void shouldValidNameCity() {
        assertEquals(servisPage.getСorrectNameCity(), servisPage.getErrorNameSity());
    }

}
