package com.dsb.test;

import java.util.Date;

import com.dsb.tools.DateUtils;
import com.dsb.tools.Util;

public class TestString {

	public static void main(String[] args) throws Exception {
/*		
		for (int j = 0; j < 100; j++) {
			//System.out.println(j);
			if ((j+1)%10==0) {
				System.out.println(j+"haha");
			}
		}
*/
		/*try {
			//当前线程休眠10s
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder("abcdefg");
		sb.deleteCharAt(sb.lastIndexOf("d"));
		System.out.println(sb.toString());*/
		/*
		String info1 = "0000010001";
		String info2 = "0001000000";
		System.out.println(info1.substring(0, info2.indexOf("1"))+info2.substring(info2.indexOf("1"), info2.indexOf("1")+1)+info1.substring(info2.indexOf("1")+1));
		*/
		/*
		TestString t = new TestString();
		System.out.println(t.getA());
		*/
		/*
		String a = "10010001";
		System.out.println(a.substring(0, 2-1)+"1"+a.substring(2));
		*/
		/*
		String s = 10001+""+100;
		System.out.println(s);
		*//*
		StringBuilder fullSql = new StringBuilder("delete from cm_user_recommend ");
		fullSql.substring(10, fullSql.lastIndexOf("end"));
		System.out.println(fullSql.toString());*/
		
		
//		System.out.println("0".equals(0));	  		//false
//		System.out.println("0".equals("0"));	  	//false
//		System.out.println(Integer.valueOf("0")==0);	//TRUE
		
//		String level = "10000000";
//		String v = String.valueOf(level.charAt(0));
//		System.out.println(v);
//		int iv = Integer.parseInt(v);
//		System.out.println(iv == 1);
		
//		StringBuilder sbUserLevel = new StringBuilder("20000000");
////		sbUserLevel.insert(0, 1);
//		sbUserLevel.replace(0, 1, "1");
//		System.out.println(sbUserLevel);
//		
//		String s = "1001";
//		String[] ss = s.split(",");
//		for (String string : ss) {
//			System.out.println(string);
//		}
		
//		String nowStr = DateUtils.date2Str(new Date(), DateUtils.FORMAT_YYYY_MM_DD___HH__MM__SS);
//		System.out.println(nowStr);
//		System.out.println(nowStr.substring(10));
//		
//		System.out.println("101".equals("101"));
//		
//		System.out.println(StringUtils.isBlank("  "));
//		
//		String s = null;
//		
//		StringBuilder sb = new StringBuilder("");
//		sb.append(s);
//		sb.append("asasa");
//		System.out.println(sb);
//		System.out.println(sb.reverse());
//
//		String path = TestString.class.getResource("/").toString();
//		System.out.println(path);
//		ClassLoader cl = Thread.currentThread().getContextClassLoader();
//		URL url = cl.getResource("");
//		String path2 = url.getPath();
//		
		// 占位符格式化String
//		System.out.println(path2);
//		int num = 1;
//		String str = String.format("%04d", num);
//		System.out.println(str);
//				
//		System.out.println(String.format("%02d", 2));
		
//		String sql = "SELECT T.* FROM (  SELECT T1.*,  (SELECT GROUP_CONCAT(T3.FNAME) FROM T_LEXIN_DEPT T3 WHERE FIND_IN_SET(T3.FID, T1.FUSE_DEPT)) FUSE_DEPT_NAME,  (SELECT GROUP_CONCAT(T4.FVALUE) FROM T_TASK_PROPERTIES T4 WHERE T4.FTASK_ID = T1.FID AND T4.FKEY = '审核人' AND T4.FVALID = '1') FAUDITORS,  (SELECT COUNT(1) FROM T_TASK_TEMPLATE T2 WHERE T2.FLEVEL = 2 AND T2.FPARENT_ID = T1.FID AND T2.FVALID = 1) FSTEP_NUM  FROM T_TASK_TEMPLATE T1 WHERE T1.FVALID = 1  AND (T1.FCREATER = '325'  OR '325' IN (SELECT T2.FVALUE FROM T_TASK_PROPERTIES T2 WHERE T2.FVALID = 1 AND T2.FKEY = '审核人' AND T2.FTASK_ID = T1.FID))  ) T WHERE 1=1  AND FLEVEL = ? ORDER BY FCREATE_TIME DESC";
		
//		System.out.println(sql.indexOf("SELECT"));
//		System.out.println(sql.indexOf("SELECT", 1));
//		System.out.println(sql.indexOf("FROM", 1));
//
//		int num = 0;
//		int selectIndex = 0;
//		int fromIndex = 0;
//		
//		do {
//			fromIndex = sql.indexOf("FROM", fromIndex);
//			selectIndex = sql.indexOf("SELECT", selectIndex + 1);
//			if (sql.substring(selectIndex, fromIndex).contains("SELECT")) {
//				num++;
//			}
//			System.out.println(sql.substring(selectIndex + 1, fromIndex));
//			System.out.println(sql.substring(selectIndex + 1, fromIndex).contains("SELECT"));
//		} while (num ==  0);
		
//		String content = "";
//		
//		int selectIndex = 0, fromIndex = 0;
//		int count = 0;
//		for (int i = 0; i < sql.length(); i++) {
//			if ("SELECT".equalsIgnoreCase(sql.substring(i, sql.length() > i + 6? i + 6 : sql.length()-1))) {
//				if (count == 0) {
//					selectIndex = i;
//				}
//				count++;
//			}
//			if ("FROM".equalsIgnoreCase(sql.substring(i, sql.length() > i + 4? i + 4 : sql.length()-1))) {
//				count--;
//				if (count == 0) {
//					fromIndex = i;
//					content = sql.substring(selectIndex + 7, fromIndex - 1);
//				}
//			}
//		}
//		
//		System.out.println(content);
		
//		System.out.println(String.format("%06d", 32222));
//		
//		Date d1 = new Date();
//		Date d2 = DateUtils.getNextMonth(d1, 1);
//
//		System.out.println(DateUtils.date2Str(d1, null));
//		System.out.println(DateUtils.date2Str(d2, null));
//
//		System.out.println(d2.after(d1));


        System.out.println(Util.createMD5("admin"));
	    
	}

	private long a = 1000000;
	
	public long getA(){
		a+=1;
		return a;
	}
}
