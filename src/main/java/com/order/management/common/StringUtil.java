package com.order.management.common;

public class StringUtil {

    public static String camelCaseToSnakeCase(String camelCaseString) {
        if (camelCaseString == null) {
            return null;
        }
        return camelCaseString.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
