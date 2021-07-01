package ru.netology.domain.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.domain.page.ServicePage;

import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebsite {

    ServicePage servicePage = new ServicePage();

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
        assertEquals(servicePage.getСorrectNameTitle(), nameTitle);

    }

    @Test
    void shouldValidNameCity() {
        assertEquals(servicePage.getСorrectNameCity(), servicePage.getErrorNameSity());
    }

}
