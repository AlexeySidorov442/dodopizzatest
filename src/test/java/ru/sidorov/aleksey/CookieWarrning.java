package ru.sidorov.aleksey;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CookieWarrning {
    public SelenideElement cookieModal = $(".cookie-policy"),
                            closeButton = cookieModal.$(".cookie-policy__button");

    public void closeCookieModal(){
        if (cookieModal.exists()) {
            closeButton.click();
        }
    }
}
