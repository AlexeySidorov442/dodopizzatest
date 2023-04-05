package ru.sidorov.aleksey.pageElements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PageHeader {
    private final SelenideElement cityInfo = $("[data-testid='header__about-slogan-text_link']");

    @Step("В хэдере отображается город {city}")
    public PageHeader checkCityDisplayedInHeader(String city) {
        cityInfo.should(visible).should(text(city));
        return this;
    }
}
