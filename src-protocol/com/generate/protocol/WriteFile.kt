package com.generate.protocol

import java.io.File
import java.io.FileOutputStream

/**
 * Created by yangzhilei on 16/9/9.
 */
class WriteFile(val protocol: Protocol, val rootPath: String) {

    fun content(): String {
        return OutputProtocol(protocol).outProtocol();
    }

    fun write() {
        createDirs()
        val path = dirPath() + protocol.className + ".java"
        try {
            val output = FileOutputStream(path);
            output.write(content().toByteArray())
            output.close()
            println("write $path success...")
        } catch (e: Exception) {
            e.printStackTrace()
            println("write $path failed...")
        }
    }

    fun dirPath(): String {
        return rootPath + protocol.packageName + "/";
    }

    fun createDirs() {
        val dir = File(dirPath());
        if (!dir.exists()) {
            val result = dir.mkdirs();
            println("create dir ${dir.absolutePath} $result")
        }
    }
}