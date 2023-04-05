package ru.sidorov.aleksey.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductOptions {
    public SelenideElement
            productOptionsModal = $(".dHDRyZ"),
            productName = $x("//h1[contains(text(),'Цыпленок ранч')]"),
            addProductToBasketButton =  $("button[data-testid='button_add_to_cart']");
}
