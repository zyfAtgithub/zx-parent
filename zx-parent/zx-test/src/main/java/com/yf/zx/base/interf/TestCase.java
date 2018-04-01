package com.yf.zx.base.interf;

import jdk.nashorn.internal.objects.annotations.Property;

import java.util.Properties;
import java.util.Set;

public class TestCase {

    public static void main(String[] args) {
        MyInterfA clazzE = new MyClazzE();
        clazzE.testA();
        Properties property = System.getProperties();
        property.setProperty("SYS_NAME", "zhangyifeng");
        Set<Object> keySet = property.keySet();
        for (Object key : keySet) {
            System.out.println(key + ":" + property.get(key));
        }

    }
}
