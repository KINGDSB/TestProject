package com.dsb.test;

import java.util.ArrayList;
import java.util.List;

class GenericClass<E> {
	void genericFunction(List<String> stringList) {
		stringList.add("foo");
	}
	// some other code
}

public class Test2 {
	public static void main(String args[]) {

		GenericClass genericClass = new GenericClass();
		List<Integer> integerList = new ArrayList<Integer>();

		genericClass.genericFunction(integerList);
		integerList.add(100);
		System.out.println(integerList.get(0));
		System.out.println(integerList.get(1));
	}
}