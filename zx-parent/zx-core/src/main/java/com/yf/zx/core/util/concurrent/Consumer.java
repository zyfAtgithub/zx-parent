package com.yf.zx.core.util.concurrent;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{  
  
    protected BlockingQueue<String> queue = null;  
  
    public Consumer(BlockingQueue<String> queue) {  
        this.queue = queue;  
    }  
  
    public void run() { 
    	while(true) {
    		try {  
    			System.out.println("消费：" + queue.take());  
    		} catch (InterruptedException e) {  
    			e.printStackTrace();  
    		}  
    	}
    }  
}  