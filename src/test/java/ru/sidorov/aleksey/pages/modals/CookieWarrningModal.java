package ru.sidorov.aleksey.pages.modals;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CookieWarrningModal {
    public SelenideElement cookieModal = $(".cookie-policy"),
                            closeButton = cookieModal.$(".cookie-policy__button");

    @Step("Закрыть модальное окно с запросом разрешения cookie, если отображается")
    public void closeCookieModalIfDisplayed(){
        if (cookieModal.exists()) {
            closeButton.click();
        }
    }
}
