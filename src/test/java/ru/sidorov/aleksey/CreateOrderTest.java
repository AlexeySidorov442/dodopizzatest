package ru.sidorov.aleksey;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateOrderTest {

    CityModal cityModal = new CityModal();
    HeaderWithStats headerWithStats = new HeaderWithStats();
    CookieWarrning cookieWarrning = new CookieWarrning();
    private final String SELECT_CITY = "Усинск";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void createOrderWithPizza(){
        //Открыть сайт
        open("https://dodopizza.ru/");

        //Окно выбора города
        cityModal.citySelectModalWindow.should(visible);

        //Выбрать город усинск для оформления заказа
        cityModal.citySearch(SELECT_CITY);

        //Проверить отображение города в главном меню
        headerWithStats.headerAbout.$(By.linkText("Усинск")).should(Condition.visible);

        //Закрытие куки сообщения
        cookieWarrning.closeCookieModal();

        //Найти пиццу Цыпленок ранч
        $$("#pizzas a").findBy(text("Цыпленок ранч")).should(Condition.visible);

        //Найденную пиццу добавить
        $x("//*[@id='pizzas']//a[contains(text(),'Цыпленок ранч')]//ancestor::article//*[contains(text(),'Выбрать')]").click();

        $x("//h1[contains(text(),'Цыпленок ранч')]").should(Condition.visible);

        //Добавить в корзину
        $("button[data-testid='button_add_to_cart']").click();
        $(".notification-enter-done").should(Condition.visible);
        $$(".notification-enter-done div").findBy(text("Цыпленок ранч, 30 см")).should(Condition.visible);
        $x("//button[contains(text(),'Корзина')]//div[@data-quantity]").shouldBe(attribute("data-quantity","1"));
        $x("//h1[contains(text(),'Цыпленок ранч')]").shouldNot(visible);

        //Перейти в корзину
        $x("//button[contains(text(),'Корзина')]").click();
        $("h1.cart-title").shouldHave(text("1 товар на 939 ₽"));

        //Добавление соуса к заказу
        $x("//h1[contains(text(),'Добавить к заказу?')]/..//h3[contains(text(), 'Соусы')]").click();
        $x("//h2[contains(text(), 'Чесночный')]/ancestor::article/button[@data-testid='product__button']").click();
        $(".sc-1wh5hgc-1").$(" .card-close").click();

        $("h1.cart-title").shouldHave(text("2 товара на 979 ₽"));

        $x("//*[contains(@class,'iMYFrd')]//*[contains(text(),'К оформлению заказа')]").click();
        $x("//*[contains(@class,'sm')]//*[contains(text(),'Вход на сайт')]").should(visible);
    }
}
