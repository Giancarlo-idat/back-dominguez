package com.store.dominguez.util.validations;

import java.math.BigDecimal;

public class Validations {

    public static boolean isBlank(String value) { return value == null || value.trim().isEmpty();}
    public static boolean isValidNames(String value) {return value != null && value.matches("^[0-9]+$");}
    public static boolean isValidEmail(String email) { return email != null && !email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");}
    public static boolean isValidTelephone(String value) { return value != null && !value.matches("^[0-9]{9}$");}
    public static boolean isValidBigDecimal(BigDecimal  value) { return value != null && value.compareTo(BigDecimal.ZERO) > 0 && isBigDecimalFormatValid(value.toString()); }
    public static boolean isValidNumber(int value ) { return String.valueOf(value).matches("^[0-9]+$");}
    public static boolean isValidStock(int value) { return value > 0;}
    public static boolean isValidRUC(String value) {return value != null && value.matches("^(20|10)\\d{9}$");}
    public static boolean isValidDNI(String value) { return value != null && value.matches("^\\d{8}$"); }
    public static boolean isBigDecimalFormatValid(String value) {
        try {
            new BigDecimal(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isValidPassword(String value) {
        return value != null && value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    }
}
