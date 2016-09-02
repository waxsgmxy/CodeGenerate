package com.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

    public abstract static class TT<T> {

        public void test() {
            System.out.println(getActualType(getClass().getGenericSuperclass()));
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
