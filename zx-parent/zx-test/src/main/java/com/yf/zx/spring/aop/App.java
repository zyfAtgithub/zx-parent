package com.yf.zx.spring.aop; /**
 * 利用Spring aop的组件， 实现简单的打印日志，拦截方法以修改方法，体验spring的轻量级。 
 */  
  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.ClassPathResource;  
  
/** 
 * Hello world! 
 * 
 */  
public class App   
{  
    public static void main( String[] args )  
    {  
        System.out.println( "Hello World!" );  
        XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("beans2.xml"));
        ServiceBean service = (ServiceBean)factory.getBean("service");  
       service.addUser("hehe", "111");  
        service.addUser("haha", "222");  
        service.findUser("haha");  
        service.deleteUser("haha");  
        service.addUser("heihei", "333");  
        System.out.println("hehe's password is "+service.getPassword("hehe"));  
          
    }  
}  