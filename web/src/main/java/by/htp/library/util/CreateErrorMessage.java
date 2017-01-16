package by.htp.library.util;

import by.htp.library.dao.pool.ConnectionPool;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class CreateErrorMessage {
    private static Logger logger = LogManager.getLogger(CreateErrorMessage.class);
    private final static String LOCALE_PROPERTIES = "localization.locale";

    public static String createErrorMessage(String errorType, String userLocale) {
        Locale locale;
        String errorMessage = "ERROR";
        locale = (userLocale != null) ? new Locale(userLocale) : Locale.getDefault();
        try{
            ResourceBundle bundle = ResourceBundle.getBundle(LOCALE_PROPERTIES, locale);
            errorMessage = bundle.getString(errorType);
        }catch (MissingResourceException e){
            logger.fatal(e);
            throw new RuntimeException("No access to the localization file", e);
        }
        return errorMessage;
    }
}
