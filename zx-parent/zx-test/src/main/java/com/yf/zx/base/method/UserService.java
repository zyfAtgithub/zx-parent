package com.yf.zx.base.method;

import java.lang.reflect.Method;

public class UserService {

    public void saveUser(String name) {
        System.out.println("save user" + name + "success!!");
    }

    public static void main(String[] args) throws Exception {
        //UserService userService = new UserService();

        Class clazz = Class.forName("com.yf.zx.base.method.UserService");

        Method method = clazz.getMethod("saveUser");

    }
}
