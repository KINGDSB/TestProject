package com.dsb.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DBUtil<T> {
	
	@SuppressWarnings("rawtypes")
	public List<T> Query(String paramSQL, Class paramClass) {
		List<T> FList=null;
		List<String> FColumns=new ArrayList<String>();
		Statement FStateMent=null;
		ResultSet FResult=null;
		T FTarget=null;
		Connection FConnection=null;
		HashMap<String, Method> FMethods=new HashMap<String,Method>();
		try {
			@SuppressWarnings("unchecked")
			Class<T> FClass=(Class<T>)Class.forName(paramClass.getName());
			FTarget=(T)FClass.newInstance();
			//TODO 获取连接
			//FConnection=getConnect();
			FStateMent = FConnection.createStatement();
			FResult = FStateMent.executeQuery(paramSQL);
			for(int i=1;i<=FResult.getMetaData().getColumnCount();++i){
				FColumns.add(FResult.getMetaData().getColumnLabel(i));
			}
			Method[] FMethodList=FTarget.getClass().getDeclaredMethods();
			for(int i=0;i<FMethodList.length;++i){
				FMethods.put(FMethodList[i].getName().toLowerCase(),FMethodList[i]);
			}
			while(FResult.next()){
				if(FList==null){
					FList=new ArrayList<T>();
				}
				if(FTarget==null){
					FTarget=FClass.newInstance();
				}
				
				for(int i=0;i<FColumns.size();++i){
					Object ob=FResult.getObject(FColumns.get(i));
					Method FMethod=FMethods.get("set"+FColumns.get(i).toLowerCase().replaceAll("_", ""));
					if(FMethod==null){
						System.out.println("方法："+"set"+FColumns.get(i).toLowerCase().replaceAll("_", "")+"不存在！");
					}
					if(ob!=null){
						if(ob.getClass()==java.math.BigDecimal.class){
							if(((java.math.BigDecimal)ob).floatValue()==((java.math.BigDecimal)ob).intValue()){
								Class[] FParamArray=FMethod.getParameterTypes();
								if(FParamArray[0]==Boolean.class){
									FMethod.invoke(FTarget,((java.math.BigDecimal)ob).intValue()==1);
								}else{
									FMethod.invoke(FTarget,((java.math.BigDecimal)ob).intValue());
								}
							}else{
								FMethod.invoke(FTarget,((java.math.BigDecimal)ob).floatValue());
							}
						}else{
							if(FMethod!=null){
								FMethod.invoke(FTarget,ob);
							}
						}
					}
				}
				FList.add(FTarget);
				FTarget=null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			if(FResult!=null){
				try {
					FResult.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(FStateMent!=null){
				try {
					FStateMent.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(FConnection!=null){
				try {
					FConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return FList;
	}
	
	/**
	 * 接执行SQL
	 * @Function DataManageUtils.execute
	 *
	 * @param fullSql 执行sql语句, 代码示例:
	  	update SEC_PRVRULE_DET r
		   set r.STATE      = :state_0,
		       r.OP_ID      = :opId,
		       r.OPER_NO    = :operNo,
		       r.STATE_DATE = :stateDate,
		       r.SO_DATE    = :soDate
		 where r.RULE_ID in (:ruleId_0)
		   and r.STATE <> :state_1
		被绑定的变量必须以 冒号(:)开始, 空格(" ")结束,变量名必须唯一
	 * @param	paramMap 绑定变量值,与被绑定的变量必须一一对应,可以不考虑顺序
	 *
	 */
	public static int execute(String fullSql, Map paramMap) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			//log.error("execute执行SQL是-->"+fullSql);
			//绑定连接
			conn = null;
			stmt = conn.prepareStatement(fullSql);
			
			//解析绑定变量方法			
			String[] keyTmpName = fullSql.split(":");
			
			for(int j=2; j<keyTmpName.length+1; j++){
				String keyName = keyTmpName[j-1].substring(0, keyTmpName[j-1].indexOf(" "));
//				if(StringUtils.isBlank(keyName)){
//					continue;
//				}
				
				int i=j-1;
				if ((paramMap.get(keyName)) instanceof java.lang.Integer) {
					int value = Integer.valueOf( (paramMap.get(keyName)).toString()).intValue();
					stmt.setInt(i, value);
				} else if ((paramMap.get(keyName)) instanceof java.lang.String) {
					stmt.setString(i, (paramMap.get(keyName)).toString());
				} else if ((paramMap.get(keyName)) instanceof java.lang.Double) {
					double d = Double.valueOf((paramMap.get(keyName)).toString()).doubleValue();
					stmt.setDouble(i, d);
				} else if ((paramMap.get(keyName)) instanceof java.lang.Float) {
					float f = Float.valueOf((paramMap.get(keyName)).toString()).floatValue();
					stmt.setFloat(i, f);
				} else if ((paramMap.get(keyName)) instanceof java.lang.Long) {
					long l = Long.valueOf((paramMap.get(keyName)).toString()).longValue();
					stmt.setLong(i, l);
				} else if ((paramMap.get(keyName)) instanceof java.lang.Boolean) {
					boolean b = Boolean.valueOf((paramMap.get(keyName)).toString()).booleanValue();
					stmt.setBoolean(i, b);
				} else if ((paramMap.get(keyName)) instanceof java.sql.Date) {
					Date date = Date.valueOf((paramMap.get(keyName)).toString());
					stmt.setDate(i, date);
				} else if ((paramMap.get(keyName)) instanceof java.sql.Timestamp) {
					Timestamp t = Timestamp.valueOf((paramMap.get(keyName)).toString());
					stmt.setTimestamp(i, t);
				} else{
					stmt.setString(i, (paramMap.get(keyName)).toString());
				}
				//log.debug("绑定变量"+i +"--"+ keyName +": "+(paramMap.get(keyName)));
			}
			//执行
			stmt.execute();
			
			int iCount = stmt.getUpdateCount();
			//log.error("处理了"+iCount +"条记录");
			
			return iCount;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * ResultSet 转 List<Map<String, Object>>
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> retCursorToList(ResultSet rs)
			throws SQLException {

		ResultSetMetaData metaData = null;
		String colunmName = null;
		Object colunmValue = null;
		Map<String, Object> mapData = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		metaData = rs.getMetaData();
		while (rs.next()) {
			mapData = new ConcurrentHashMap<String, Object>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				colunmName = metaData.getColumnName(i).toLowerCase();
				colunmValue = rs.getObject(colunmName);
				// 先不处理大字段
				/*
				 * if(colunmValue instanceof CLOB){ colunmValue =
				 * this.setClob(rs.getClob(colunmName)); }
				 */

				if (colunmValue != null) {
					mapData.put(colunmName, colunmValue.toString());
				} else {
					mapData.put(colunmName, "");
				}
			}
			list.add(mapData);
		}
		return list;

	}

//	/**
//	 * 查询 add by chenzx
//	 **/
//	public static List<Map<String, Object>> execQueryList(String sql,
//			Object[] values) throws Exception {
//
//		Connection conn = null;
//		PreparedStatement stmt = null;
//
//		// 日志记录
////		log.info("sql : " + sql);
//		if (log.isInfoEnabled()) {
//			if (values != null && values.length > 0) {
//				for (int i = 0; i < values.length; i++) {
////					log.info("param" + i + " : " + values[i]);
//				}
//			}
//		}
//
//		ResultSet rs = null;
//		List<Map<String, Object>> retList = null;
//
//		try {
//
//			conn = ServiceManager.getSession().getConnection();
//			stmt = conn.prepareStatement(sql);
//			setParameters(stmt, values);
//			rs = stmt.executeQuery();
//			retList = retCursorToList(rs);
//
//		} finally {
//			if (stmt != null) {
//				stmt.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//		}
//
//		return retList;
//	}
//
//	private static List<Map<String, Object>> retCursorToList(ResultSet rs)
//			throws SQLException {
//
//		ResultSetMetaData metaData = null;
//		String colunmName = null;
//		Object colunmValue = null;
//		Map<String, Object> mapData = null;
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		metaData = rs.getMetaData();
//		while (rs.next()) {
//			mapData = new ConcurrentHashMap<String, Object>();
//			for (int i = 1; i <= metaData.getColumnCount(); i++) {
//				colunmName = metaData.getColumnName(i).toLowerCase();
//				colunmValue = rs.getObject(colunmName);
//				// 先不处理大字段
//				/*
//				 * if(colunmValue instanceof CLOB){ colunmValue =
//				 * this.setClob(rs.getClob(colunmName)); }
//				 */
//
//				if (colunmValue != null) {
//					mapData.put(colunmName, colunmValue.toString());
//				} else {
//					mapData.put(colunmName, "");
//				}
//			}
//			list.add(mapData);
//		}
//		return list;
//
//	}
//
//	/**
//	 * 
//	 * add by chenzx
//	 * 
//	 * */
//	protected static void setParameters(PreparedStatement pstmt, Object[] params)
//			throws Exception {
//		if (params != null) {
//			for (int i = 0; i < params.length; i++) {
//				pstmt.setObject(i + 1, params[i]);
//			}
//		}
//	}

}
