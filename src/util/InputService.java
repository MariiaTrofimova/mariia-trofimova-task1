package util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputService {
    public static final String INPUT_LASTNAME = "Please, enter last name";
    public static final String INPUT_FIRSTNAME = "Please, enter first name";
    public static final String PATTERN_NAME = "[a-zA-Zа-яА-яЁё-]+";
    public static final String ERROR = "Incorrect input format";

    public static String inputLastName(Scanner scanner) {
        System.out.println(INPUT_LASTNAME);
        String lastName = scanner.nextLine();
        if (!isValidName(lastName)) {
            System.out.println(ERROR);
            return inputFirstName(scanner);
        }
        return capitalize(lastName);
    }

    public static String inputFirstName(Scanner scanner) {
        System.out.println(INPUT_FIRSTNAME);
        String firstName = scanner.nextLine();
        if (!isValidName(firstName)) {
            System.out.println(ERROR);
            return inputFirstName(scanner);
        }
        return capitalize(firstName);
    }

    private static boolean isValidName(String s) {
        return s != null && s.length() > 0 && checkPattern(s);
    }

    private static boolean checkPattern(String s) {
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static String capitalize(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 || chars[i - 1] == '-') {
                chars[i] = Character.toUpperCase(chars[i]);
            } else {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        return String.valueOf(chars);
    }
}