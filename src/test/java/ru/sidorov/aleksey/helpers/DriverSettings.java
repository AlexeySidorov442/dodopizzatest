package ru.sidorov.aleksey.helpers;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import ru.sidorov.aleksey.config.Config;

public class DriverSettings {

    private static final Config config = ConfigFactory.create(Config.class);

    public static void configureDriver() {
        Configuration.browser = config.browser();
        Configuration.browserSize = config.browserSize();
        Configuration.baseUrl = config.baseUrl();
    }
}
