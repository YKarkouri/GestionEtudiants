package ma.est.gestionetudiants.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Common {

    public static String formatDate(LocalDateTime date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
}
