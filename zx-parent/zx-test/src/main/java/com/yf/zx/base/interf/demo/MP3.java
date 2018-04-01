package com.yf.zx.base.interf.demo;

class MP3 implements USB {
      public void install() {
           System.out.println("安装MP3驱动程序。") ;
      }
      public void work() {
           System.out.println("进行MP3拷贝。") ;
      }
}