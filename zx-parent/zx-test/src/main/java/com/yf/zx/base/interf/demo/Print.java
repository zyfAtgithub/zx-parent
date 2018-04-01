package com.yf.zx.base.interf.demo;

class Print implements USB {
      public void install() {
           System.out.println("安装打印机驱动程序。") ;
      }
      public void work() {
           System.out.println("进行文件打印。") ;
      }
}