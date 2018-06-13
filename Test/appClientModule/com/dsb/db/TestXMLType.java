package com.dsb.db;

import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleResultSet;
import oracle.sql.CLOB;
import oracle.sql.OPAQUE;
import oracle.xdb.XMLType;

/**
 * 
 * 
 * java 读取 oracle 中 xmltype 类型的字段 
 * -- Create table
 * 
 * @version0.1 create table T_XMLTYPE ( C_XMLTYP xmltype ) ;
 * 
 * @author Administrator
 * 
 */
public class TestXMLType {

	public static void main(String[] args) {

		ArrayList param = new ArrayList();
		StringBuffer str = new StringBuffer("");

		str.append("INSERT INTO T_XMLTYPE");
		str.append("(C_XMLTYP)");
		str.append("values(sys.XMLTYPE.createXML(?))");

		StringBuffer xmlnews = new StringBuffer();

		xmlnews.append("<NewsItem xmlns=\"http://www.oracle.com/NewsItem.xsd\"");
		xmlnews.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		xmlnews.append(" xsi:schemaLocation=\"http://www.oracle.com/NewsItem.xsd ");
		xmlnews.append(" http://www.oracle.com/NewsItem.xsd\">");
		xmlnews.append("<title>");
		xmlnews.append("标题");
		xmlnews.append("</title>");
		xmlnews.append("<description>");
		xmlnews.append("描述");
		xmlnews.append("</description>");
		xmlnews.append("<entered_date>");
		xmlnews.append("录入日期");
		xmlnews.append("</entered_date>");
		xmlnews.append("<expiry_date>");
		xmlnews.append("过期日期");
		xmlnews.append("</expiry_date>");
		xmlnews.append("<imagepointer>");
		xmlnews.append("图像");
		xmlnews.append("</imagepointer>");
		xmlnews.append("<typeid>");
		xmlnews.append("类型");
		xmlnews.append("</typeid></NewsItem>");

		param.add(xmlnews.toString());

		// 测试插入新项目
		TestXMLType xmltest = new TestXMLType();
		try {
			xmltest.insertItem(str.toString(), param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ArrayList aList = xmltest.editItem(
					"SELECT C_XMLTYP FROM T_XMLTYPE WHERE ROWNUM = 1", null);
			System.out.println(aList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 插入新项目
	 * 
	 * @param query
	 * @param params
	 * @throws Exception
	 */
	public void insertItem(String query, ArrayList params) throws Exception {

		CLOB clob = null;

		// Initialize statement Object
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = getConnection();

			pstmt = con.prepareStatement(query);

			if (params != null) {
				for (int i = 0; i < params.size(); i++) {

					clob = this.getCLOB((String) params.get(i), con);
					pstmt.setObject((i + 1), clob);

				}
			}

			pstmt.execute();

			pstmt.close();

		} finally {

			if (pstmt != null) {
				pstmt.close();
			}

			if (clob != null) {
				freeCLOB(clob);
			}

			releaseConnection(con);

		}
	}

	/**
	 * 读取内容
	 * 
	 * @param query
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ArrayList editItem(String query, ArrayList params) throws Exception {
		Connection con = null;

		ResultSet rs = null;

		ArrayList data = new ArrayList();
		try {
			con = getConnection();

			PreparedStatement pst = con.prepareStatement(query);

			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pst.setObject(i + 1, params.get(i));
				}
			}

			rs = pst.executeQuery();

			while (rs.next()) {
				OracleResultSet ors = (OracleResultSet) rs;
				OPAQUE op = ors.getOPAQUE(1);
				XMLType xml = XMLType.createXML(op);

				data.add(xml.getStringVal());
			}
			
//			rs = pst.executeQuery();
//			OracleResultSet ors = (OracleResultSet) rs.unwrap(OracleResultSet.class);
//			while (ors.next()) {
//				
//				OPAQUE op = ors.getOPAQUE(1);
//				XMLType xml = XMLType.createXML(op);
//
//				data.add(xml.getStringVal());
//			}

			pst.close();

			return data;

		} catch (Exception e) {
			throw e;
		} finally {

			releaseConnection(con); // Releases the connection

		}
	}

	/**
	 * 释放临时CLOB文件
	 * 
	 * @param clob
	 * @throws DBException
	 */
	private void freeCLOB(CLOB clob) throws Exception {
		try {
			clob.freeTemporary();
		} catch (SQLException sqlexp) {
			throw new Exception(sqlexp.getMessage());
		}
	}

	/**
	 * 释放连接
	 * 
	 * @param conn
	 * @throws DBConException
	 */
	private void releaseConnection(Connection conn) throws Exception {
		try {
			if (conn != null || !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException ex) {
			throw new Exception("不能关闭数据库连接。" + ex.getMessage());
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws Exception
	 */
	private Connection getConnection() throws Exception {
		String user = "DSS_SYYB";
		String password = "DSS_SYYB";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.131.229:1521:orcl";
		Connection c = null;

		Class.forName(driver).newInstance();
		c = DriverManager.getConnection(url, user, password);
		return c;

	}

	/**
	 * 获取CLOB数据类型
	 * 
	 * @param clobData
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private CLOB getCLOB(String clobData, Connection conn) throws Exception {
		CLOB tempClob = null;

		try {
			// 创建新的临时CLOB
			tempClob = CLOB.createTemporary(conn, true, CLOB.DURATION_SESSION);

			// 以读写模式打开临时CLOB
			tempClob.open(CLOB.MODE_READWRITE);

			// 获取输出流用来写入
			Writer tempClobWriter = tempClob.getCharacterOutputStream();

			// 将字符数据写到临时clob中
			tempClobWriter.write(clobData);

			// 刷新和关闭
			tempClobWriter.flush();
			tempClobWriter.close();

			// 关闭临时clob
			tempClob.close();

			return tempClob;
		} catch (Exception exp) {
			tempClob.freeTemporary();
			throw new Exception("Exception thrown in getCLOB method "
					+ "of DBBroker class of given status : " + exp.getMessage());
		}
	}
}
