package com.test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yangzhilei on 2016/10/19.
 */
public class Tool {

    static boolean isBean(Class cls) {
        return !isInt(cls) && !isFloat(cls) && !isLong(cls) && !isDouble(cls) && !isString(cls) && !isBigDecimal(cls) && !isList(cls) && !isBoolean(cls);
    }

    static boolean isBoolean(Class cls) {
        return boolean.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls);
    }

    static boolean isString(Class cls) {
        return String.class.isAssignableFrom(cls);
    }

    static boolean isInt(Class cls) {
        return int.class.isAssignableFrom(cls) || Integer.class.isAssignableFrom(cls);
    }

    static boolean isLong(Class cls) {
        return long.class.isAssignableFrom(cls) || Long.class.isAssignableFrom(cls);
    }

    static boolean isFloat(Class cls) {
        return float.class.isAssignableFrom(cls) || Float.class.isAssignableFrom(cls);
    }

    static boolean isDouble(Class cls) {
        return double.class.isAssignableFrom(cls) || Double.class.isAssignableFrom(cls);
    }

    static boolean isBigDecimal(Class cls) {
        return BigDecimal.class.isAssignableFrom(cls);
    }

    static boolean isList(Class cls) {
        return List.class.isAssignableFrom(cls);
    }
}
