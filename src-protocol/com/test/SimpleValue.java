package com.test;

import java.math.BigDecimal;

/**
 * Created by yangzhilei on 2016/10/31.
 * 包装基础数据类型
 */
public class SimpleValue {

    public static final int NO_VALUE = Integer.MIN_VALUE;

    public static final int TYPE_NULL = -1;
    public static final int TYPE_INT = 1;
    public static final int TYPE_LONG = 2;
    public static final int TYPE_FLOAT = 3;
    public static final int TYPE_DOUBLE = 4;
    public static final int TYPE_BOOLEAN = 5;
    public static final int TYPE_BIGDECIMAL = 6;

    int type = TYPE_NULL;
    Object value;

    public static SimpleValue obtain(Object value) {
        return new SimpleValue(value);
    }

    public SimpleValue(Object value) {
        setValue(value);
    }

    public void setValue(Object value) {
        this.value = value;
        if (value == null) {
            type = TYPE_NULL;
            return;
        }
        Class cls = value.getClass();
        if (Tool.isInt(cls)) {
            type = TYPE_INT;
        } else if (Tool.isLong(cls)) {
            type = TYPE_LONG;
        } else if (Tool.isFloat(cls)) {
            type = TYPE_FLOAT;
        } else if (Tool.isDouble(cls)) {
            type = TYPE_DOUBLE;
        } else if (Tool.isBoolean(cls)) {
            type = TYPE_BOOLEAN;
        } else if (Tool.isBigDecimal(cls)) {
            type = TYPE_BIGDECIMAL;
        }
    }

    public Object getValue() {
        return value;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isInt() {
        return type == TYPE_INT;
    }

    public boolean isLong() {
        return type == TYPE_LONG;
    }

    public boolean isFloat() {
        return type == TYPE_FLOAT;
    }

    public boolean isDouble() {
        return type == TYPE_DOUBLE;
    }

    public boolean isBoolean() {
        return type == TYPE_BOOLEAN;
    }

    public boolean isBigDecimal() {
        return type == TYPE_BIGDECIMAL;
    }

    public int intValue() {
        return bigDecimalValue().intValue();
    }

    public long longValue() {
        return bigDecimalValue().longValue();
    }

    public float floatValue() {
        return bigDecimalValue().floatValue();
    }

    public double doubleValue() {
        return bigDecimalValue().doubleValue();
    }

    public String strValue() {
        if (!isNull()) {
            return value.toString();
        }
        return null;
    }

    public BigDecimal bigDecimalValue() {
        BigDecimal def = new BigDecimal(NO_VALUE);
        if (!isNull()) {
            try {
                return new BigDecimal(value + "");
            } catch (Exception e) {
                return def;
            }
        }
        return def;
    }

    public boolean booleanValue() {
        if (isBoolean()) {
            return value == Boolean.TRUE;
        }
        return "true".equals(value) || "yes".equals(value);
    }
}