package ua.kruart.address_book.utils;

import ua.kruart.address_book.model.Lang;

import java.util.Locale;

/**
 * Created by Arthur on 16.11.2016.
 */
public class LocaleManager {

    public static final Locale EN_LOCALE = new Locale("en");
    public static final Locale UA_LOCALE = new Locale("ua");
    public static final Locale RU_LOCALE = new Locale("ru");


    private static Lang currentLang;

    public static Lang getCurrentLang() {
        return currentLang;
    }

    public static void setCurrentLang(Lang currentLang) {
        LocaleManager.currentLang = currentLang;
    }
}
