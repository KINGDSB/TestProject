package com.dsb.test;

import java.util.ArrayList;

/**
 * 写Java代码分别使堆溢出,栈溢出
 * 
 * @author admin
 *
 */
public class Out {

    public void testHeap(){
        for(;;){
              ArrayList list = new ArrayList (2000);
          }
    }
    int num=1;
    public void testStack(){
        num++;
        this.testStack();
     }
    
    public static void main(String[] args){
        Out  t  = new Out ();
        t.testHeap();
        t.testStack();   
    }
}
