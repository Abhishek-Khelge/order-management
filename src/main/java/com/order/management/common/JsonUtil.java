package com.order.management.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static ObjectMapper defaultMapper;

    public static <T> T str2obj(String str, Class<T> clazz) throws Exception {
        return defaultMapper.readValue(str, clazz);
    }

    public static String obj2str(Object obj, Boolean maskData) throws Exception {
        if (obj == null) {
            return null;
        }

        return defaultMapper.writeValueAsString(obj);
    }

}
