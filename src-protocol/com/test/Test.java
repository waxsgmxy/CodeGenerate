package com.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzhilei on 16/8/22.
 */
public class Test {

    ArrayList<TT> list;

    public static void main(String[] args) {
        new TT<TT>() {
        }.test();

        try {
            System.out.println(getActualType(Test.class.getDeclaredField("list").getGenericType()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    static class A {
        public A() {
            this("");
        }

        public A(String str) {
            System.out.println("A...");
        }
    }

    static class B extends A {

    }

    public abstract static class TT<T> {

        public void test() {
            System.out.println(getActualType(getClass()));
        }
    }

    static <T> Class<T> getActualType(Type type) {
        if (type instanceof ParameterizedType) {
            try {
                Class<T> pType = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
                return pType;
            } catch (Exception e) {
            }
        }
        return null;
    }

}
