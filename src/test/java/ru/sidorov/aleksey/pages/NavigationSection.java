package ru.sidorov.aleksey.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NavigationSection {

    public SelenideElement getSectionByName(String sectionName){
        return $("section[id="+sectionName+"]");
    }

    public SelenideElement findItemInSection(String sectionName, String item){
        return $$("section[id="+sectionName+"] a").findBy(Condition.text(item));
    }

    public void selectItem(String sectionName, String item){
        findItemInSection(sectionName, item).$x(".//ancestor::article//*[contains(text(),'Выбрать')]").click();
    }

    @Step("Выбрать пиццу {pizza} в разделе {section}")
    public void selectPizza(String pizza, String section){
        $$("section[id="+section+"] a").findBy(Condition.text(pizza))
                .$x(".//ancestor::article//*[contains(text(),'Выбрать')]").click();
    }

}
