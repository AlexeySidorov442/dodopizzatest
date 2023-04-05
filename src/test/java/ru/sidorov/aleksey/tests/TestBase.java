package ru.sidorov.aleksey.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.sidorov.aleksey.helpers.AllureAttachments;
import ru.sidorov.aleksey.helpers.DriverSettings;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
        DriverSettings.configureDriver();
    }

    @AfterEach
    public void addAttachments() {
        AllureAttachments.addScreenshotAs("Скриншот последней страницы");
        Selenide.closeWebDriver();
    }
}
