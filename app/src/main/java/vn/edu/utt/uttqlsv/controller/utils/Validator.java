package vn.edu.utt.uttqlsv.controller.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final String FLOATING_NUMBER_PATTERN = "[+-]?[0-9]+(\\\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
    private static final String DATE_PATTERN = "^\\d{2}/\\d{2}/\\d{4}";

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidFloatingNumber(String f) {
        Pattern pattern = Pattern.compile(FLOATING_NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(f);
        return matcher.matches();
    }

    public static boolean isValidDateString(String date) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

}
