package com.generate.protocol

/**
 * Created by yangzhilei on 16/8/25.
 */
class Protocol(val packageName: String, val className: String, val operation: String, val requestWithoutCookie: Boolean) {

    val requests: MutableList<Field> = arrayListOf();

    var response: BeanDeclare? = null;

    val beanPool = mutableMapOf<String, BeanDeclare>();

    fun fieldMapping(field: Field): String {
        when (field.fieldType) {
            FieldType.INT -> return "int"
            FieldType.LONG -> return "long"
            FieldType.FLOAT -> return "float"
            FieldType.DOUBLE -> return "double"
            FieldType.BIGDECIMAL -> return "BigDecimal"
            FieldType.STRING -> return "String"
            FieldType.BEAN -> return beanMapping(field)
            FieldType.LIST -> return listMapping(field)
        }
        return "";
    }

    fun beanMapping(field: Field): String {
        return "";
    }

    fun listMapping(field: Field): String {
        return "";
    }

    fun addBeanDeclare(beanDeclare: BeanDeclare) {
        beanPool.put(beanDeclare.className, beanDeclare);
    }

    fun getBeanDeclare(className: String): BeanDeclare? {
        return beanPool.get(className);
    }
}

enum class FieldType {

    INT, LONG, FLOAT, DOUBLE, BIGDECIMAL, STRING, BEAN, LIST;

}

class BeanDeclare(val className: String) {

    val fields: MutableList<Field> = arrayListOf();

}

class BeanField(val className: String, key: String, name: String?) : Field(key, FieldType.BEAN, name) {

}

class ListField(val key: String, val name: String?) {

}

open class Field(val key: String, val fieldType: FieldType, val name: String?) {

    constructor(key: String, fieldType: FieldType) : this(key, fieldType, null);

    /**
     * 获取变量名
     */
    fun getFieldName(): String {
        if (name != null) {
            return name;
        }
        return key;
    }
}