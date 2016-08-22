package com.test;

/**
 * Created by yangzhilei on 16/8/22.
 */
public class HomeBean {

    @Protocol(key = "name")
    private String name;

    @Protocol(key = "num")
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
