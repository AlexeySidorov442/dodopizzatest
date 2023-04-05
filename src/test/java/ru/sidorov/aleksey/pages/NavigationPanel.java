package ru.sidorov.aleksey.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class NavigationPanel {
    public SelenideElement
            navigationPanel = $(".grid"),
            basketButton = $x("//button[contains(text(),'Корзина')]"),
            basketItems = $x("//button[contains(text(),'Корзина')]//div[@data-quantity]");

    public SelenideElement gridTab(String tab){
        return $$(".grid ul li").findBy(Condition.text(tab));
    }

    public void goToTab(String tab){
        gridTab(tab).click();
    }

    public void basketItemsCountShouldHaveValue(int itemCount){
        basketItems.shouldBe(Condition.attribute("data-quantity", ""+itemCount));
    }
}
