package com.test;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by yangzhilei on 16/8/22.
 */
public class Test {

//    ArrayList<TT> list;

    public static void main(String[] args) {

        double max = 1.35;
        int v = (int) Math.round(max * 100);
        double maxY = (v / 5 + 1) * 5 / 100d;
        System.out.println(new DecimalFormat("######.00").format(maxY));

        long l = 10000l;
        System.out.println(new DecimalFormat("###,###").format(l));


//        new TT<TT>() {
//        }.test();
//
//        try {
//            System.out.println(getActualType(Test.class.getDeclaredField("list").getGenericType()));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }

//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        ListIterator<Integer> iterator = list.listIterator();
//        while (iterator.hasNext()) {
//            int value = iterator.next();
//            System.out.println(value);
//            if (value == 2) {
//                iterator.remove();
//            }
//        }
//        System.out.println(list);
//
//        Object object1 = new Object();
//        WeakReference<Object> object2 = new WeakReference<Object>(object1);
//        object1 = null;
//        System.gc();
//        System.out.println(object2.get());
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
