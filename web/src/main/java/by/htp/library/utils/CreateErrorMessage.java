package by.htp.library.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class CreateErrorMessage {
    private final static String LOCALE_PROPERTIES = "localization.locale";

    public static String createErrorMessage(String errorType, String userLocale) {
        Locale locale;
        if (userLocale == null) {
            locale = Locale.getDefault();
        } else {
            locale = new Locale(userLocale);
        }
        ResourceBundle bundle = ResourceBundle.getBundle(LOCALE_PROPERTIES, locale);
        String errorMessage = bundle.getString(errorType);
        return errorMessage;
    }
}
