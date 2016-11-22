package com.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yangzhilei on 16/8/22.
 */
public class Test {

//    ArrayList<TT> list;

    public static void main(String[] args) {

        SimpleValue simpleValue = new SimpleValue("232.220909");
        System.out.println(simpleValue.intValue());
        System.out.println(simpleValue.longValue());
        System.out.println(simpleValue.doubleValue());
        System.out.println(simpleValue.floatValue());
        System.out.println(simpleValue.bigDecimalValue());
        System.out.println(simpleValue.booleanValue());

        String s = new String("ser");
        String ss = "ser";

        System.out.println(s.hashCode());
        System.out.println(ss.hashCode());

        System.out.println(s == ss);

//        Integer v = new Integer(2);
//
//        System.out.println(v != 0);

//        double max = 1.35;
//        int v = (int) Math.round(max * 100);
//        double maxY = (v / 5 + 1) * 5 / 100d;
//        System.out.println(new DecimalFormat("######.00").format(maxY));
//
//        long l = 10000l;
//        System.out.println(new DecimalFormat("###,###").format(l));

//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = sf.parse("2017-01-01");
//            System.out.println(date.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

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