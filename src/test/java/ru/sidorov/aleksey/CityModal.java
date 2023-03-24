package ru.sidorov.aleksey;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CityModal {
    public SelenideElement
            citySelectModalWindow = $("locality-selector-popup"),
            citySelectInput = $("input.locality-selector-popup__search-input");

    public void citySearch(String city){
        citySelectInput.click();
        citySelectInput.sendKeys(city);
        citySelectInput.pressEnter();
    }

}
