package ru.sidorov.aleksey.Tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.Attachment;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import ru.sidorov.aleksey.Pages.*;
import ru.sidorov.aleksey.Utils.ServerConfig;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class CreateOrderTest extends BaseConfigurationTests{

    //Init all pages
    CityModal cityModal = new CityModal();
    HeaderWithStats headerWithStats = new HeaderWithStats();
    CookieWarrning cookieWarrning = new CookieWarrning();
    NavigationSection navigationSection = new NavigationSection();
    ProductOptions productOptions = new ProductOptions();
    NotificationWindow notificationWindow = new NotificationWindow();
    NavigationPanel navigationPanel = new NavigationPanel();
    ConfirmOrder confirmOrder = new ConfirmOrder();

    //Init constants
    private final String SELECT_CITY = "Усинск";
    private final String SELECT_PIZZA = "Цыпленок ранч";
    private final int ITEM_COUNT = 1;
    private final String SELECT_SAUCE = "Чесночный";

    //Init Owner Library property for choose browser
    static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);


    @Attachment(value = "Скриншот", type = "image/png")
    public byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка основной бизнес цепочки добавления товара в корзину и переход к оформлению заказа")
    public void createOrderWithPizza(){
        //Открыть сайт
        step("Открыть главную страницу", ()->{
            open("https://dodopizza.ru/");
        });


        step("Выбор города в списке", ()->{
            //Окно выбора города
            cityModal.citySelectModalWindow.should(visible);

            //Выбрать город усинск для оформления заказа
            cityModal.citySearch(SELECT_CITY);
        });

        //Проверить отображение города в главном меню
        step("Открыта главная страница доставки выбранного города" + SELECT_CITY, ()->{
            headerWithStats.headerAbout.$(By.linkText(SELECT_CITY)).should(Condition.visible);
        });

        step("Закрытие куки предупреждения, если отображается", ()->{
            //Закрытие куки сообщения
            cookieWarrning.closeCookieModal();
        });

        step("Поиск пиццы" + SELECT_PIZZA + " и ее выбор", ()->{
            //Найти пиццу Цыпленок ранч
            navigationSection.findItemInSection("pizzas", SELECT_PIZZA).should(visible);

            //Найденную пиццу выбрать
            navigationSection.selectItem("pizzas", SELECT_PIZZA);
        });

        step("Добавление в корзину из окна доп. опций", ()->{
            productOptions.productName.should(visible);
            productOptions.addProductToBasketButton.click();
        });

        //Отображение всплывающего окна о добавленном товаре
        step("Отображение всплывающего окна с добавленным товаром" + SELECT_PIZZA, ()->{
            notificationWindow.notification.should(visible);
            notificationWindow.notificationProductInfo.should(visible);
            productOptions.productName.shouldNot(visible);
        });

        step("Отображение счетчика товаров в корзине" + ITEM_COUNT, ()->{
            navigationPanel.basketItemsCountShouldHaveValue(1);
        });

        step("Открытие корзины", ()->{
            navigationPanel.basketButton.click();
            confirmOrder.headerInfoOrder.shouldHave(text("1 товар на 939 ₽"));
        });

        //Добавление соуса к заказу
        step("Добавление опций к заказу", ()->{
            confirmOrder.addSauceButton.click();
            confirmOrder.selectSauce(SELECT_SAUCE);
            confirmOrder.saucesWindowCloseButton.click();
            confirmOrder.headerInfoOrder.shouldHave(text("2 товара на 979 ₽"));
            takeScreenshot();
        });


        step("Оформление заказа", ()->{
           confirmOrder.confirmOrderButton.click();
            takeScreenshot();
           confirmOrder.logInModal.should(visible);
        });
    }
}
