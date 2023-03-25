package ru.sidorov.aleksey.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

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
}
