package com.dsb.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test03 {

	//http://imgsrc.baidu.com/forum/pic/item/dd663087e950352ac63e77395b43fbf2b2118b3d.jpg
	
	public void draw(int num){
		
		int side = num*2-1;
		
		int[][] arr = new int[side][side];
		
		arr[num-1][num-1] = 1;
		
		int j = 1;
		
		for (int i = num-1 ; i < 0; i-- , j ++ ) {
			
			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Entity e = new Entity();
		System.out.println(e.getI());
		
		String str1 ="How are you "; 
		String str2 ="you are How  "; 
		String str3 ="Qou are How  "; 
		boolean result =  matchString(str1, str2);
		System.out.println(result);
		

		System.out.println(matchString1(str1,str2));
		System.out.println(matchString1(str3,str2));
		

		String str4 ="a a b"; 
		String str5 ="a b"; 
		
		System.out.println(matchString1(str4,str5));
		
		
	}

	public static boolean matchString(String str1,String str2){
		String [] arr1 = str1.split(" "); 
		String [] arr2 = str2.split(" "); 
		boolean flag= false;
		int count = 0;
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr1.length; j++) {
				if (arr1[j].equals(arr2[i])) {
					count++;
					break;
				}
			}
		}
		if (count==arr1.length) {
			flag= true;
		}
		return flag;
	}

	public static boolean matchString1(String str1,String str2){

		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		
		Collections.addAll(set1, str1.split(" "));
		Collections.addAll(set2, str2.split(" "));
		
		return set1.containsAll(set2);
		
	}
	
	public static boolean matchString2(String str1,String str2){

		List<String> list1 = Arrays.asList(str1.split(" "));
		List<String> list2 = Arrays.asList(str2.split(" "));

		Collections.sort(list1);
		Collections.sort(list2);
		
		return list1.equals(list2);
		
	}
	
}

class Entity{
	private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
}
