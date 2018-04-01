package com.yf.zx.base.interf.demo;

public class TestDemo {
    public static void main(String args[]) {
        Computer c = new Computer() ;
        c.plugin(new Phone()) ;
        c.plugin(new Print()) ;
        c.plugin(new MP3());
    }
}