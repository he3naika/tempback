package uk.co.castlewater.myaccount.integration.service.util;

/**
 * @author Anatol Sialitski
 */
public class StringUtil {

    private StringUtil() {
        throw new AssertionError();
    }

    public static String nullToNothing(Object value) {
        return value == null ? "" : value.toString();
    }

    public static String nothingToNull(Object value) {
        return value == null || "".equals(value) ? null : value.toString();
    }

}
