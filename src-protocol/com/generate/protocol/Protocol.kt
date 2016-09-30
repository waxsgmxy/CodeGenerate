package com.generate.protocol

/**
 * Created by yangzhilei on 16/8/25.
 */
class Protocol(val packageName: String, val className: String, val operation: String, val requestWithoutCookie: Boolean) {

    var responseClass: String? = null;

    val requests: MutableList<Field> = arrayListOf();

    val beanPool = mutableMapOf<String, BeanDeclare>();

    fun addRequest(field: Field) {
        requests.add(field)
    }

    fun addBeanDeclare(vararg beanDeclares: BeanDeclare) {
        for (beanDeclare in beanDeclares) {
            beanPool.put(beanDeclare.className, beanDeclare);
        }
    }

    fun isBeanDeclareExits(className: String): Boolean {
        return beanPool.get(className) != null;
    }

    fun getBeanDeclare(className: String): BeanDeclare? {
        return beanPool.get(className);
    }
}

enum class FieldType {

    INT, LONG, FLOAT, DOUBLE, BIGDECIMAL, STRING, BEAN, LIST, BOOLEAN;

}

class BeanDeclare(val className: String) {

    val fields: MutableList<Field> = arrayListOf();

    fun addField(field: Field) {
        fields.add(field);
    }

}

open class Field(val fieldType: FieldType, val key: String, val name: String?) {

    constructor(fieldType: FieldType, key: String) : this(fieldType, key, null);

    var beanDeclareClass: String? = null;

    var mockValue: Any? = null;

    /**
     * 获取变量名
     */
    fun getFieldName(): String {
        if (name != null) {
            return name;
        }
        return key;
    }

    fun setBeanDeclare(beanDeclareClass: String): Field {
        this.beanDeclareClass = beanDeclareClass;
        return this;
    }

    fun setMockValue(mockValue: Any): Field {
        this.mockValue = mockValue;
        return this;
    }

    fun getBeanDeclare(): String {
        if (beanDeclareClass == null) {
            return "Object";
        }
        return beanDeclareClass!!;
    }
}

fun fieldMapping(field: Field): String {
    when (field.fieldType) {
        FieldType.INT -> return "int"
        FieldType.LONG -> return "long"
//        FieldType.FLOAT -> return "float"
//        FieldType.DOUBLE -> return "double"
        FieldType.FLOAT -> return "String"
        FieldType.DOUBLE -> return "String"
        FieldType.BIGDECIMAL -> return "BigDecimal"
        FieldType.STRING -> return "String"
        FieldType.BOOLEAN -> return "boolean"
        FieldType.BEAN -> return beanMapping(field)
        FieldType.LIST -> return listMapping(field)
    }
    return "";
}

private fun beanMapping(field: Field): String {
    return field.getBeanDeclare();
}

private fun listMapping(field: Field): String {
    return "ArrayList<${field.getBeanDeclare()}>";
}