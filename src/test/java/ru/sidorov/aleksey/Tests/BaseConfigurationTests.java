package ru.sidorov.aleksey.Tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class BaseConfigurationTests {
    @BeforeAll
    static void beforeAll() {
        //Paste this default settings for all tests
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
        Configuration.browserSize = "1920x1080";
    }
}
