package com.dsb.test;

import java.util.Stack;

public class TestBinary {
	
	
	
    public static void main(String[] args) {
    	
    	
    	System.out.println(~11);
    	System.out.println(Integer.toBinaryString(11));
    	System.out.println(Integer.toBinaryString(~11));
    	System.out.println(Integer.toBinaryString(12));
    	System.out.println(Integer.toBinaryString(-12));
    	System.out.println(Integer.toBinaryString(~-12));
    	System.out.println(~-12);
    	System.out.println(-~12);
    	
    	Integer.parseInt("1111111111111111111111111110100", 2);
    	
    }
    
  

    /**
     * 获取十进制数num在base进制的值
     * @param num
     * @param base
     * @return
     */
	public static String baseString(int num, int base) {
		if (base > 16) {
			throw new RuntimeException("进制数超出范围，base<=16");
		}
		StringBuffer str = new StringBuffer("");
		String digths = "0123456789ABCDEF";
		Stack<Character> s = new Stack<Character>();
		boolean isMinus = false;// 判断输入的数是不是负数
		String numStr = String.valueOf(num);
		if (numStr .charAt(0) == '-') {
			num = Integer.valueOf(numStr.substring(1));
			isMinus = true;
		}
		while (num != 0) {
			s.push(digths.charAt(num % base));
			num /= base;
		}
		if (isMinus)
			str.append("-");
		while (!s.isEmpty()) {
			str.append(s.pop());
		}
		return str.toString();
	}
    
}
