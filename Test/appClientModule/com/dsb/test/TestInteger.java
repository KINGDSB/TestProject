package com.dsb.test;

import java.util.ArrayList;
import java.util.List;

public class TestInteger {
	public static void main(String args[]) {

		Integer i1 = new Integer(8);
		Integer i2 = new Integer(8);
		Long l1 = new Long(8);
		Integer i3 = 9;
		Integer i4 = 9;

		System.out.println(i1 == i2);
		System.out.println(i1.equals(i2));
		System.out.println(i1.equals(l1));
		System.out.println(8 == i2);
		
		System.out.println(i3 == i4);
	}
}