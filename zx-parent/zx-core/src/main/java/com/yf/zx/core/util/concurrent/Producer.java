package com.yf.zx.core.util.concurrent;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{  
  
    protected BlockingQueue<String> queue = null;  
  
    public Producer(BlockingQueue<String> queue) {  
        this.queue = queue;  
    }  
  
    public void run() {  
    	int i = 1;
    	while (true) {
    		try { 
    			queue.put(i+"");  
    			System.out.println("生产" + i);
    			Thread.sleep(1000); 
    			i++;
    		} catch (InterruptedException e) {  
    			e.printStackTrace();  
    		}  
    	}
    }  
}  