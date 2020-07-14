package service;

public class UsefulMethods {

    public static boolean isNumber(String input) {
        if (input.length() == 0) return false;
        for (int i = 0; i < input.length(); i++)
            if (!Character.isDigit(input.charAt(i))) return false;
        return true;
    }
}
