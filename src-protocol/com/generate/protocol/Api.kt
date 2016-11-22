package com.generate.protocol

/**
 * Created by yangzhilei on 16/9/12.
 */

val version = "0.0.2";

var useSimpleValue = false;//是否使用基础数据类型

/**
 * 根据协议对象生成目标代码
 */
fun generateProtocol(protocol: Protocol): String {
    return OutputProtocol(protocol).outProtocol();
}

/**
 * 根据请求 返回 json字符串生成Protocol对象
 */
fun parseJson(parseJson: ParseJson, requestJson: String?, responseJson: String?): Protocol {
    parseJson.parse(requestJson, responseJson);
    return parseJson.protocol
}

/**
 * 创建协议生成文件
 */
fun createWriteFile(protocol: Protocol, rootPath: String): WriteFile {
    return WriteFile(protocol, rootPath);
}

/**
 * 根据协议对象生成目标代码写入文件
 */
fun writeProtocol(protocol: Protocol, rootPath: String) {
    WriteFile(protocol, rootPath).write();
}