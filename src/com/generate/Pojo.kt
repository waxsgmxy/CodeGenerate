package com.generate

/**
 * Created by yangzhilei on 16/8/19.
 */
data class Pojo(val packageName: String, val name: String, val imports: MutableList<String> = arrayListOf()) {

}

class Field {

}

abstract class Method(val name: String, val params: MutableList<Field> = arrayListOf()) {



}

fun main(args: Array<String>) {
    val pojo = Pojo("", "")
}