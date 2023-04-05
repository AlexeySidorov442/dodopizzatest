package ru.sidorov.aleksey.pages.modals;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.sidorov.aleksey.pages.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CityModal {
    public SelenideElement
            citySelectModalWindow = $(".locality-selector-popup"),
            citySelectInput = $("input.locality-selector-popup__search-input");


    @Step("Выбрать город {city} в модальном окне выбора города")
    public MainPage selectCity(String city) {
        citySelectModalWindow.should(visible);
        citySelectInput.click();
        citySelectInput.sendKeys(city);
        citySelectInput.pressEnter();
        return new MainPage();
    }

}
