package ru.sidorov.aleksey.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sidorov.aleksey.pages.modals.CityModal;
import ru.sidorov.aleksey.pages.NavigationPanel;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class DescriptionAboutUs extends TestBase {

    CityModal cityModal = new CityModal();
    NavigationPanel navigationPanel = new NavigationPanel();

    //Init constants
    private final String SELECT_CITY = "Усинск";
    private final String SELECT_TAB = "О нас";

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Информация в блоках о нас")
    public void checkDescriptionInAboutUsBlock(){
        //Открыть сайт
        step("Открыть главную страницу", ()->{
            open("https://dodopizza.ru/");
        });

        step("Выбор города в списке", ()->{
            //Окно выбора города
            cityModal.citySelectModalWindow.should(visible);

            //Выбрать город усинск для оформления заказа
            cityModal.selectCity(SELECT_CITY);
        });

        step("Переход на страницу " + SELECT_TAB, ()->{
            navigationPanel.gridTab(SELECT_TAB).should(visible);
            navigationPanel.goToTab(SELECT_TAB);
        });


    }
}
