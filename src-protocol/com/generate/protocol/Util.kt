package com.generate.protocol

import java.io.BufferedReader
import java.io.FileReader

/**
 * Created by yangzhilei on 16/9/12.
 */
fun loadFile(path: String): String {
    val builder = StringBuilder();
    val reader = FileReader(path);
    val bufferedReader = BufferedReader(reader);
    var str: String? = null;
    while ({ str = bufferedReader.readLine();str }() != null) {
        builder.append(str);
    }
    bufferedReader.close();
    reader.close();
    return builder.toString();
}

fun upperFirst(str: String): String {
    return str.first().toUpperCase() + str.substring(1)
}

fun lowerFirst(str: String): String {
    return str.first().toLowerCase() + str.substring(1)
}