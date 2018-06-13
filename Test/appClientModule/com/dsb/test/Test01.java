package com.dsb.test;

import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.Instant;
import java.util.Date;

public class Test01 {
	
	public static void main(String[] args) throws InterruptedException {
		
		/*
		long[] info = {100,10,31,43,12,44,12,44,22,42,55};
		for (int i = 0; i < info.length; i++) {
			long[] temp = new long[2];
			if ((i+1)%2==0) {
				for (int j = 0; j > i-2; j--) {
					
				}
			}
		}*/
		
//		int [][][][][][] arr = new int[1][2][3][4][5][6];
		
		/*
		Set<Long> set = new HashSet<Long>();
		set.add(10001l);
		set.add(10002l);
		set.add(10002l);
		set.add(10003l);
		System.out.println(set.size());
		set.remove(10003l);
		System.out.println(set.size());
		for (Long long1 : set) {
			System.out.println(long1);
		}
		*/
		/*
		NULL[] nulls = new NULL[10];
		for (NULL tmp : nulls) {
			if (null == tmp) {
				System.out.println("null");
			}
		}
		*/
		/*
		String sss = "ab,cde,fg";
		System.out.println(sss);
		sss = sss.substring(0, sss.lastIndexOf("g"));
//		sss = sss.substring(sss.indexOf(",")+1);
		System.out.println(sss);
		*/
		
//		System.out.println(2%5);
		//枚举测试
		/*
		System.out.println("============");
		System.out.println("展示所有");
		String a = FenZu.showAll();
		System.out.println(a);
		System.out.println("============");
		System.out.println("展示单个");
		System.out.println(FenZu.FRIEND_LIST3.show());
		*/
//		Long num = 1l;
//		TestList tl = new TestList();
//		tl.getNum(num);
//		System.out.println(num);
		
//		Set<Long> setl = new HashSet<Long>();
//		setl.add(10001l);
//		setl.add(10002l);
//		setl.add(10003l);
//		setl.add(10004l);
//		System.out.println(setl.add(10001l));
//		System.out.println(setl.add(10005l));
		
//		JSONObject jsonObject = new JSONObject();
		
//		int i = 0;
//		String s = "";
//		if (i <= 0 && "".equals(s)) {
//			System.out.println("i <= 0 && .equals(s)");
//			return;
//		}
//		
//		if (i > 0) {
//			System.out.println("i > 0");
//		}else{
//			System.out.println("i <= 0");
//		}
		
		
		// java中 | 分割要用 \\| 拆分
//		String picList = "http://dd-feed.digi123.cn/201603/f5bd0dcd401b5e87.jpg|http://dd-feed.digi123.cn/201603/8fc77182efe93fdf.jpg|http://dd-feed.digi123.cn/201603/ca829fdda95af5c9.jpg";
//		String[] arrPic = picList.split("|");
//		System.out.println("原字符串:\n"+picList);
//		System.out.println("拆分后:\n"+Arrays.toString(arrPic));
//		for (int i = 0; i < arrPic.length; i++) {
//			System.out.println("拆分后循环"+i+"次:\n"+arrPic[i]);
//		}
		
//		Timestamp t = new Timestamp(new Date().getTime());
//		System.out.println(t.toString());
		
//		String suid = UUID.randomUUID().toString();
//		String uid = suid.replaceAll("-", "").toUpperCase();
//		System.out.println("suid："+suid);
//		System.out.println("uid："+uid);
		
//		
//		Integer i = null;
//		System.out.println("5" + i); //1
//		System.out.println(5 + i); //2
		

//		Date date1 = new Date();
//		Date date2 = new Date();
//		date.setHours(10);
//		date1.setSeconds(10);
//		long ldate = date1.getTime();
//
//		System.out.println(ldate);
//		System.out.println(date2.getTime());

		
//		// 增加时间
//		try {
//			
//			String addDate = "02:20:00";
//			String[] addDates = addDate.split(":");
//			
//			Timestamp timestamp = new Timestamp(new Date().getTime());
//			Timestamp t = DateTime.addHour(timestamp, Integer.parseInt(addDates[0]));
//			t = DateTime.addMinute(t, Integer.parseInt(addDates[1]));
//			t = DateTime.addSecond(t, Integer.parseInt(addDates[2]));
//
//			System.out.println(timestamp);
//			System.out.println(t);
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(new Timestamp(1464847333362l));
//
//		
//		for (int i = 0; i < 10; i++) // 1
//		System.out.println(i);
//
//		for (int i = 0; i < 10; i++) ; // 2
		
		
//		
//		Date date = new Date();
//		boolean b1 = date instanceof Date; //1
//		boolean b2 = date instanceof Object; //2
//		boolean b3 = date instanceof String; //3
		
		
//		List<Integer> list = Arrays.asList(new Integer[]{});
//		int[] arr = new int[30];
//		
//		while (list.size() >0) {
//			
//			
//			
//		}
//		 
//		
//		System.out.println(getStrNum("ABBCCCCAA"));
//		
		
//		String s = "ss";
//		getStr(s);
//		System.out.println(s);
		
//		FenZu fz = FenZu.FRIEND_LIST2;
//		getFenzu(fz);
//		System.out.println(fz.getMsg());
		
//		System.out.println(URLDecoder.decode("\u7b7e\u540d\u8bc1\u4e66\u7c7b\u578b"));
		
//		Collections.reverse(new ArrayList<String>());
//		
//		Set<String> setStr = new HashSet<String>();
//		setStr.add("as");
		
//		flag:
//			for (int i = 0; i < 100; i++) {
//				System.out.println(i);
//				if (10 == i) {
//					break flag;
//				}
//			}
//		System.out.println("aaa");
		
		BigDecimal b = new BigDecimal("100.0");
		System.out.println(b.intValue());
		
		Date date1 = new Date(new Date().getTime()+1000);
		Date date2 = new Date();
		
		long l = date1.getTime() - date2.getTime();
		
		Thread.currentThread().sleep(1000);

		Date date3 = new Date();
		Date date4 = new Date(date3.getTime()+l);

		System.out.println("date3:"+date3.getTime());
		System.out.println("date4:"+date4.getTime());
		
		Instant time = Instant.now();
		System.out.println(time);
		
	}
	
	public static String getStrNum(String str){
		StringBuilder sb = new StringBuilder();
		
		char tmpChar = str.charAt(0);
		int tmpCount = 1;
		
		for (int i = 1; i < str.length(); i++) {
			
			char currentChar = str.charAt(i);
			
			if (tmpChar == currentChar) {
				tmpCount++;
			} else {
				sb.append(tmpChar);
				sb.append(tmpCount);
				
				tmpChar = currentChar;
				tmpCount = 1;
			}
			
			if (i == str.length()-1) {
				sb.append(tmpChar);
				sb.append(tmpCount);
			}
			
		}
		
		return sb.toString();
	}
	
	
	private void load(String jarName){
//		String jarName = "C.jar";
		try {
			File file = new File(jarName);
			URL url = file.toURL();
			URLClassLoader loader = new URLClassLoader(new URL[]{url});
			Class aClass = loader.loadClass("C");
			Object obj = aClass.newInstance();
			aClass.getMethods();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getFenzu(FenZu fz){
		fz = FenZu.FRIEND_LIST1;
	}
	
	public static void getStr(String s){
		s = "aa";
		System.out.println(s);
	}
}
