package ru.sidorov.aleksey.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class NotificationWindow {
    public SelenideElement
            notification = $(".notification-enter-done"),
            notificationProductInfo = $$(".notification-enter-done div").findBy(text("Цыпленок ранч, 30 см"));
}
