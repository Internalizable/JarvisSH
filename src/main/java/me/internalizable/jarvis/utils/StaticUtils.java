package me.internalizable.jarvis.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class StaticUtils {

    /**
     * A method that gets the formatted divider of our CLI.
     *
     * @return the formatted divider used in CLI.
     */
    public static String getDivider() {
        return getFormattedText("---------------------------------------------", FormatMarkdown.STRIKETHROUGH);
    }

    /**
     * Format our text in the command line given the markdown.
     *
     * @param text     - The text that will be converted.
     * @param markdown - The markdown type to apply.
     * @return formatted text.
     */
    public static String getFormattedText(String text, FormatMarkdown markdown) {
        return "\033[" + markdown.getCode() + "m" + text + "\033[0m";
    }

    /**
     * Converts a date to a local date. Used for comparing dates.
     *
     * @param dateToConvert - The date to convert into local date.
     * @return a LocalDate
     */
    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
    }

}
