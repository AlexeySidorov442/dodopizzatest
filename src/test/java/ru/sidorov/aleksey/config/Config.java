package ru.sidorov.aleksey.config;


@org.aeonbits.owner.Config.Sources({
        "classpath:config/config.properties"
})
public interface Config extends org.aeonbits.owner.Config {

    @Key("browser")
    String browser();

    @Key("browserSize")
    String browserSize();

    @Key("baseUrl")
    String baseUrl();
}
