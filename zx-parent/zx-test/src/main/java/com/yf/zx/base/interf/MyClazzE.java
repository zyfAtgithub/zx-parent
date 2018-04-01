package com.yf.zx.base.interf;

/**
 * 继承抽象类
 *
 * 1、若是抽象类实现了接口，但没有实现对应接口的方法，则在抽象类的子类中需要实现
 * 2、若是抽象类实现了接口，且实现了对应接口的方法，则在抽象类的子种可以不用实现
 */
public class MyClazzE extends MyAbsClazzD {

    @Override
    public void testA() {
        System.out.println("子类MyclazzE重写接口MyInterfA中的抽象方法tesA");
    }

    @Override
    public void testB() {
        System.out.println("子类MyclazzE重写接口MyInterfB中的抽象方法tesB");
    }
}
