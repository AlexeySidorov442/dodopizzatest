package ru.sidorov.aleksey.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ConfirmOrder {
    public SelenideElement
            headerInfoOrder = $("h1.cart-title"),
            addSauceButton = $x("//h3[contains(text(), 'Соусы')]"),
            saucesWindowCloseButton = $(".card-close"),
            confirmOrderButton = $x("//button[contains(text(), 'К оформлению заказа')]"),
            logInModal = $x("//*[contains(text(),'Вход на сайт')]");

    public void selectSauce(String sauce){
        $x("//h2[contains(text(), '"+ sauce +"')]/ancestor::article/button[@data-testid='product__button']").click();
    }
}
