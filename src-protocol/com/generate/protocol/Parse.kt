package com.generate.protocol

import org.json.JSONArray
import org.json.JSONObject
import java.math.BigDecimal

/**
 * Created by yangzhilei on 16/9/12.
 */
class ParseJson(val packageName: String, val className: String, val operation: String, val requestWithoutCookie: Boolean) {

    lateinit var protocol: Protocol;
    var ignoreFields = StringBuilder(",clientId,appId,resultCode,resultMsg,traceNo,");
    val beanMappings: MutableMap<String, String> = mutableMapOf();

    init {
        protocol = Protocol(this.packageName, this.className, this.operation, this.requestWithoutCookie)
    }

    fun parse(requestJson: String?, responseJson: String?) {
        if (requestJson != null) {
            parseRequest(JSONObject(requestJson))
        }
        if (responseJson != null) {
            parseResponse(JSONObject(responseJson))
        }
    }

    fun getResponseDeclare(): BeanDeclare? {
        if (protocol.responseClass == null || protocol.getBeanDeclare(protocol.responseClass!!) == null) {
            protocol.addBeanDeclare(initResponseDeclare())
        }
        return protocol.getBeanDeclare(protocol.responseClass!!);
    }

    fun addBeanMapping(className: String, mappingClassName: String) {
        beanMappings.put(className, mappingClassName)
    }

    fun setResponseClass(className: String): ParseJson {
        protocol.responseClass = className
        return this
    }

    private fun isIgnoreField(key: String): Boolean {
        return ignoreFields.contains(",$key,");
    }

    private fun parseRequest(jsonObject: JSONObject) {
        val set = jsonObject.keySet();
        set.forEach {
            key ->
            if (!isIgnoreField(key)) {
                val value = jsonObject.get(key)
                protocol.addRequest(analysis(key, value))
            }
        }
    }

    private fun initResponseDeclare(): BeanDeclare {
        if (protocol.responseClass == null) {
            protocol.responseClass = "Response"
        }
        return BeanDeclare(protocol.responseClass!!)
    }

    fun parseResponse(jsonObject: JSONObject) {
        val responseDeclare = initResponseDeclare();
        val set = jsonObject.keySet()
        set.forEach {
            key ->
            if (!isIgnoreField(key)) {
                val value = jsonObject.get(key)
                responseDeclare.addField(analysis(key, value))
            }
        }
        protocol.addBeanDeclare(responseDeclare)
    }

    fun addBean(jsonObject: JSONObject, className: String) {
        //已存在，忽略
        if (protocol.isBeanDeclareExits(className)) {
            return;
        }
        val beanDeclare = BeanDeclare(className);
        val set = jsonObject.keySet()
        set.forEach {
            key ->
            val value = jsonObject.get(key)
            beanDeclare.addField(analysis(key, value))
        }
        protocol.addBeanDeclare(beanDeclare)
    }

    fun analysis(key: String, value: Any): Field {
        when (value) {
            is Int -> return Field(FieldType.INT, key)
            is Long -> return Field(FieldType.LONG, key)
            is Float -> return Field(FieldType.FLOAT, key)
            is Double -> return Field(FieldType.DOUBLE, key)
            is String -> return Field(FieldType.STRING, key)
            is BigDecimal -> return Field(FieldType.BIGDECIMAL, key)
            is Boolean -> return Field(FieldType.BOOLEAN, key)
            is JSONObject -> {
                var className = upperFirst(key);
                if (beanMappings.get(className) != null) {
                    className = beanMappings.get(className)!!
                }
                addBean(value, className)
                return Field(FieldType.BEAN, key).setBeanDeclare(className)
            }
            is JSONArray -> {
                return analysisList(key, value)
            }
        }
        return Field(FieldType.BEAN, key).setBeanDeclare("Object")
    }

    fun analysisList(key: String, value: JSONArray): Field {
        var className = inferClassname(key)
        if (value.length() > 0) {
            val obj = value[0];
            when (obj) {
                is Int -> className = "Integer"
                is Long -> className = "Long"
                is Float -> className = "Float"
                is Double -> className = "Double"
                is String -> className = "String"
                is BigDecimal -> className = "BigDecimal"
                is Boolean -> className = "Boolean"
                is JSONObject -> {
                    if (beanMappings.get(className) != null) {
                        className = beanMappings.get(className)!!
                    }
                    addBean(value.getJSONObject(0), className)
                }
            }
        }
        return Field(FieldType.LIST, key).setBeanDeclare(className)
    }
}

fun inferClassname(key: String): String {
    if (key.endsWith("list", true)) {
        return upperFirst(key.substring(0, key.length - 4))
    } else if (key.endsWith("array", true)) {
        return upperFirst(key.substring(0, key.length - 5))
    } else if (key.endsWith("s", true)) {
        return upperFirst(key.substring(0, key.length - 1))
    }
    return upperFirst(key)
}