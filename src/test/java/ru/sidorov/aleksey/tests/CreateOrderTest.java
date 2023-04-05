package ru.sidorov.aleksey.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.sidorov.aleksey.pages.*;
import ru.sidorov.aleksey.pages.modals.CookieWarrningModal;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;


public class CreateOrderTest extends TestBase {

    //Init all pages
    MainPage mainPage = new MainPage();
    CookieWarrningModal cookieWarrning = new CookieWarrningModal();
    NavigationSection navigationSection = new NavigationSection();
    ProductOptions productOptions = new ProductOptions();
    NotificationWindow notificationWindow = new NotificationWindow();
    NavigationPanel navigationPanel = new NavigationPanel();
    ConfirmOrder confirmOrder = new ConfirmOrder();

    //Init constants
    private final String CITY = "Усинск";
    private final String SELECT_PIZZA = "Цыпленок ранч";
    private final int ITEM_COUNT = 1;
    private final String SELECT_SAUCE = "Чесночный";

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка основной бизнес цепочки добавления товара в корзину и переход к оформлению заказа")
    public void createOrderWithPizza(){
        mainPage
                .openMainPage()
                .selectCity(CITY)
                .getPageHeader()
                .checkCityDisplayedInHeader(CITY);

        cookieWarrning
                .closeCookieModalIfDisplayed();

        navigationSection
                .selectPizza(SELECT_PIZZA, "pizzas");


        step("Добавление в корзину из окна доп. опций", ()->{
            productOptions.productName.should(visible);
            productOptions.addProductToBasketButton.click();
        });

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

        step("Добавление опций к заказу", ()->{
            confirmOrder.addSauceButton.click();
            confirmOrder.selectSauce(SELECT_SAUCE);
            confirmOrder.saucesWindowCloseButton.click();
            confirmOrder.headerInfoOrder.shouldHave(text("2 товара на 979 ₽"));
        });

        step("Оформление заказа", ()->{
            confirmOrder.confirmOrderButton.click();
            confirmOrder.logInModal.should(visible);
        });
    }
}
