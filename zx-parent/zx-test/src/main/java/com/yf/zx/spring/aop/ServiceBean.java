package com.yf.zx.spring.aop;

interface ServiceBean {
    void addUser(String userName,String password);  
    void deleteUser(String userName);  
    boolean findUser(String userName);  
    String getPassword(String userName);  
} 