package ru.sidorov.aleksey.pages;

import io.qameta.allure.Step;
import ru.sidorov.aleksey.pageElements.PageHeader;
import ru.sidorov.aleksey.pages.modals.CityModal;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private final PageHeader pageHeader = new PageHeader();

    @Step("Открыть главную страницу")
    public CityModal openMainPage() {
        open("");
        return new CityModal();
    }

    public PageHeader getPageHeader() {
        return pageHeader;
    }
}
