package com.dsb.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;


public class MySqlInfo {

    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/yyt_lx?useUnicode=true&amp;characterEncoding=utf-8";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection con = null;
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return con;
    }

//    @Test
    public void just4MySQLInfo() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        Connection con = (Connection) DriverManager.getConnection(URL,USERNAME,PASSWORD);
        DatabaseMetaData metaData = (DatabaseMetaData) con.getMetaData();
        
        /**
         * 1.返回数据库的相关信息
         */
         System.out.println("数据库已知的用户: "+ metaData.getUserName());   
         System.out.println("数据库的系统函数的逗号分隔列表: "+ metaData.getSystemFunctions());   
         System.out.println("数据库的时间和日期函数的逗号分隔列表: "+ metaData.getTimeDateFunctions());   
         System.out.println("数据库的字符串函数的逗号分隔列表: "+ metaData.getStringFunctions());   
         System.out.println("数据库供应商用于 'schema' 的首选术语: "+ metaData.getSchemaTerm());   
         System.out.println("数据库URL: " + metaData.getURL());   
         System.out.println("是否允许只读:" + metaData.isReadOnly());   
         System.out.println("数据库的产品名称:" + metaData.getDatabaseProductName());   
         System.out.println("数据库的版本:" + metaData.getDatabaseProductVersion());   
         System.out.println("驱动程序的名称:" + metaData.getDriverName());   
         System.out.println("驱动程序的版本:" + metaData.getDriverVersion()); 
         System.out.println();   
         System.out.println("数据库中使用的表类型"); 

         System.out.println("数据库列表(Catalogs):"); 
         ResultSet rsCatalogs = metaData.getCatalogs();
         while (rsCatalogs.next()) {
        	 System.out.println("1.-->"+rsCatalogs.getString(1));  
		}
         
         ResultSet rs = metaData.getTableTypes();   
         while (rs.next()) {   
             System.out.println("1.-->"+rs.getString(1));  
         }   
         rs.close();   
         System.out.println();
         
         /**  
          * 获取指定的数据库的所有表的类型,getTables()的第一个参数就是数据库名  
          * 因为与MySQL连接时没有指定,这里加上,剩下的参数就可以为null了  
          * 第二个参数是模式名称的模式,但是输出也是什么都没有。
          */  
         System.out.println("获取指定的数据库的所有表的类型");   
         ResultSet rs1 = metaData.getTables("yyt_lx", "",null, null);   
         while (rs1.next()) {   
             System.out.println();   
             System.out.println("数据库名:"+ rs1.getString(1));   
             System.out.println("表名: "+rs1.getString(3));   
             System.out.println("类型: "+rs1.getString(4));   
         }   
         rs1.close();   
            
         System.out.println();   
         System.out.println("获取指定的数据库的表的主键");   
         //获取指定的数据库的表的主键，第二个参数也是模式名称的模式,使用null了   第三个是表名称  为null表示可以查到所有的表
         ResultSet rs2 = metaData.getPrimaryKeys("yyt_lx", null, "t_task");   
         
         while (rs2.next()) {   
             System.out.println("表名称: "+ rs2.getString(3));   
             System.out.println("主键名称: "+ rs2.getString(4));   
             System.out.println("主键de序列号: "+ rs2.getString(5));   
             System.out.println("主键de名称: "+ rs2.getString(6));   
         }   
         rs2.close();   
            
         System.out.println(); 
         
         
         /**
          * 获取某个表的索引信息
          */
         System.out.println("DatabaseMetaData.getIndexInfo()方法返回信息:");   
         ResultSet rs3 = metaData.getIndexInfo("xcb", null, "t_consultation", false, true);   
         while (rs3.next()) {   
             System.out.println("数据库名: "+ rs3.getString(1));   
             System.out.println("表模式: "+ rs3.getString(2));   
             System.out.println("表名称: "+ rs3.getString(3));   
             System.out.println("索引值是否可以不唯一: "+ rs3.getString(4));   
             System.out.println("索引类别: "+ rs3.getString(5));   
             System.out.println("索引名称: "+ rs3.getString(6));   
             System.out.println("索引类型: "+ rs3.getString(7));   
             System.out.println("索引中的列序列号: "+ rs3.getString(8));   
             System.out.println("列名称: "+ rs3.getString(9));   
             System.out.println("列排序序列: "+ rs3.getString(10));   
             System.out.println("TYPE为 tableIndexStatistic时它是表中的行数否则它是索引中唯一值的数量: "+ rs3.getString(11));   
             System.out.println("TYPE为 tableIndexStatisic时它是用于表的页数否则它是用于当前索引的页数: "+ rs3.getString(12));   
             System.out.println("过滤器条件: "+ rs3.getString(13));   
         }   
         rs3.close();
         
         
         System.out.println(); 
         
         /**
          * 获取某个表的所有列信息
          */
         System.out.println("DatabaseMetaData.getColumns()方法返回数据表列信息");
         ResultSet rs4 = metaData.getColumns("xcb", null, "t_consultation", "mediaType");
         while(rs4.next()){
             System.out.println("数据库名称:"+rs4.getString(1));
             System.out.println("表模式:"+rs4.getString(2));
             System.out.println("表名称:"+rs4.getString(3));
             System.out.println("列名称:"+rs4.getString(4));
             System.out.println("SQL类型:"+rs4.getString(5));
             System.out.println("数据源依赖类型名称:"+rs4.getString(6));
             System.out.println("列的大小:"+rs4.getString(7));
             System.out.println("未被使用:"+rs4.getString(8));
             System.out.println("小数部分的位数:"+rs4.getString(9));
             System.out.println("基数:"+rs4.getString(10));
             System.out.println("是否允许NULL:"+rs4.getString(11));
             System.out.println("描述列的注释:"+rs4.getString(12));
             System.out.println("该列的默认值:"+rs4.getString(13));
             System.out.println("未使用:"+rs4.getString(14));
             System.out.println("未使用:"+rs4.getString(15));
             System.out.println("对于char类型， 该长度是列中的最大字节数:"+rs4.getString(16));
             System.out.println("表中列的索引:"+rs4.getString(17));
             System.out.println("ISO规则用于确定列是否包括NULL:"+rs4.getString(18));
             System.out.println("表的类别:"+rs4.getString(19));
             System.out.println("表的模式:"+rs4.getString(20));
             System.out.println("表名称:"+rs4.getString(21));
             System.out.println("不同类型或用户生成Ref类型:"+rs4.getString(22));
             System.out.println("此列是否自增:"+rs4.getString(23));
         }
         rs4.close();
         
    }
    
//    @Test
    public void just4AllDBInfo() throws ClassNotFoundException, SQLException{
        Connection con = getConnection();
        DatabaseMetaData metaDate = con.getMetaData();
        //1.得到数据库下所有数据表
        ResultSet rs = metaDate.getTables("yyt_lx", null, null, null);
        //2.根据表名  拼接成SQL语句  查询到某个表的所有列
        PreparedStatement prep = null;
        while(rs.next()){
            String sql = "SELECT  *  FROM `"+rs.getString(3)+"` WHERE 1=2;";
            prep = con.prepareStatement(sql);  
            ResultSet set = prep.executeQuery(sql);
            ResultSetMetaData data = set.getMetaData();
            //迭代取到所有列信息
            for (int i = 1; i <= data.getColumnCount(); i++) {
                // 获得所有列的数目及实际列数
                int columnCount = data.getColumnCount();
                // 获得指定列的列名
                String columnName = data.getColumnName(i);
                // 获得指定列的列值
                int columnType = data.getColumnType(i);
                // 获得指定列的数据类型名
                String columnTypeName = data.getColumnTypeName(i);
                // 所在的Catalog名字
                String catalogName = data.getCatalogName(i);
                // 对应数据类型的类
                String columnClassName = data.getColumnClassName(i);
                // 在数据库中类型的最大字符个数
                int columnDisplaySize = data.getColumnDisplaySize(i);
                // 默认的列的标题
                String columnLabel = data.getColumnLabel(i);
                // 获得列的模式
                String schemaName = data.getSchemaName(i);
                // 某列类型的精确度(类型的长度)
                int precision = data.getPrecision(i);
                // 小数点后的位数
                int scale = data.getScale(i);
                // 获取某列对应的表名
                String tableName = data.getTableName(i);
                // 是否自动递增
                boolean isAutoInctement = data.isAutoIncrement(i);
                // 在数据库中是否为货币型
                boolean isCurrency = data.isCurrency(i);
                // 是否为空
                int isNullable = data.isNullable(i);
                // 是否为只读
                boolean isReadOnly = data.isReadOnly(i);
                // 能否出现在where中
                boolean isSearchable = data.isSearchable(i);
                
                ResultSet pkRs = metaDate.getPrimaryKeys(catalogName, schemaName, tableName);
                // 
				while(pkRs.next() ) { 
					System.err.println("****** PRIMARY KEY ******"); 
					System.err.println("TABLE_CAT : "+pkRs.getObject(1)); 
					System.err.println("TABLE_SCHEM: "+pkRs.getObject(2)); 
					System.err.println("TABLE_NAME : "+pkRs.getObject(3)); 
					System.err.println("COLUMN_NAME: "+pkRs.getObject(4)); 
					System.err.println("KEY_SEQ : "+pkRs.getObject(5)); 
					System.err.println("PK_NAME : "+pkRs.getObject(6)); 
					System.err.println("****** ******* ******"); 
				}
                
                
                System.out.println("数据表："+rs.getString(3));
                System.out.println("数据表"+rs.getString(3)+"列总数："+columnCount);
                System.out.println("获得列" + i + "的字段名称:" + columnName);
                System.out.println("获得列" + i + "的类型,返回SqlType中的编号:"+ columnType);
                System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
                System.out.println("获得列" + i + "所在的Catalog名字:"+ catalogName);
                System.out.println("获得列" + i + "对应数据类型的类:"+ columnClassName);
                System.out.println("获得列" + i + "在数据库中类型的最大字符个数:"+ columnDisplaySize);
                System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
                System.out.println("获得列" + i + "的模式:" + schemaName);
                System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
                System.out.println("获得列" + i + "小数点后的位数:" + scale);
                System.out.println("获得列" + i + "对应的表名:" + tableName);
                System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
                System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
                System.out.println("获得列" + i + "是否为空:" + isNullable);
                System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
                System.out.println("获得列" + i + "能否出现在where中:"+ isSearchable);
                
                System.out.println();
            }
        }
        
    }
    
    public static Connection getConnection(DataSource dataSource) throws ClassNotFoundException, SQLException{
        Connection con = null;
        Class.forName(DRIVER);
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataSource.getMsg()+"?useUnicode=true&characterEncoding=GBK", USERNAME, PASSWORD);
        return con;
    }

    @Test
    public void just4Table2CN() throws ClassNotFoundException, SQLException{

        Connection conn_yyt_lx = null;
        Connection conn_yyt_lx_model = null;
        Statement stmt_yyt_lx = null;
        Statement stmt_yyt_lx_model = null;
        
        try {
        	
			conn_yyt_lx = getConnection(DataSource.YYL_LX);
			conn_yyt_lx_model = getConnection(DataSource.YYT_LX_MODEL);
			stmt_yyt_lx = conn_yyt_lx.createStatement();
			stmt_yyt_lx_model = conn_yyt_lx_model.createStatement();
			DatabaseMetaData metaDate = conn_yyt_lx.getMetaData();
			//1.得到数据库下所有数据表
			ResultSet rs = stmt_yyt_lx.executeQuery("SELECT t1.TABLE_NAME, t1.TABLE_COMMENT FROM information_schema.tables t1 WHERE t1.TABLE_SCHEMA = 'yyt_lx'");

			Map<String, String> column_NameComment = new HashMap<>();
			String tableName = null;
			String columnName = null;
			String tableComment  = null;
			String columnComment = null;
			String dropTableSqlTemp = " DROP TABLE IF EXISTS `@@TABLE_NAME@@`";
			String tableSqlTemp = "CREATE TABLE `@@TABLE_NAME@@` ( @@COLUMNS@@ ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='@@TABLE_NAME@@'";
			String columnSqlTemp = " `@@COLUMN_NAME@@` @@COLUMN_TYPE@@@@COLUMN_SIZE@@ @@COLUMN_IS_NULL@@ COMMENT '@@COLUMN_COMMENT@@' ";
			String pkSqlTemp = " PRIMARY KEY (`@@COLUMN_NAME@@`) ";
			String tableSql = null;
			StringBuilder columnsSql = null;
			String pkSql = null;
			String columnSql = null;
			
			while(rs.next()){
//				stmt = conn_yyt_lx_model.createStatement();
				columnsSql = new StringBuilder();
				tableName = rs.getString("TABLE_NAME");
//				tableName = rs.getString(3);
				tableComment = rs.getString("TABLE_COMMENT");
				ResultSet colRS = metaDate.getColumns(null, "%", tableName, "%");
				while(colRS.next()){
					
					columnName = colRS.getString("COLUMN_NAME");
					int nullable = colRS.getInt("NULLABLE");
					String TYPE_NAME = colRS.getString("TYPE_NAME");
					columnComment = colRS.getString("REMARKS");
					columnSql = columnSqlTemp.
//							replaceAll("@@COLUMN_NAME@@", colRS.getString("COLUMN_NAME")).
							replaceAll("@@COLUMN_TYPE@@", TYPE_NAME).
							replaceAll("@@COLUMN_IS_NULL@@", 0 == nullable ? "NOT NULL" : "").
							replaceAll("@@COLUMN_COMMENT@@", columnComment);

					// 字段名用备注方便建ER
					if (StringUtils.isNotBlank(columnComment)) {
						columnSql = columnSql.replaceAll("@@COLUMN_NAME@@", columnName+":"+columnComment.split(" ")[0]); 
						column_NameComment.put(colRS.getString("COLUMN_NAME"), columnName+":"+columnComment.split(" ")[0]);
					} else {
						columnSql = columnSql.replaceAll("@@COLUMN_NAME@@", columnName); 
						column_NameComment.put(colRS.getString("COLUMN_NAME"), columnName);
					}

					if ("BIT".equalsIgnoreCase(TYPE_NAME) || 
						"TIMESTAMP".equalsIgnoreCase(TYPE_NAME) ||
						"DATETIME".equalsIgnoreCase(TYPE_NAME) ||
						"DATE".equalsIgnoreCase(TYPE_NAME) ||
						"LONGTEXT".equalsIgnoreCase(TYPE_NAME) ) {
						columnSql = columnSql.replaceAll("@@COLUMN_SIZE@@", "");
					} else if ("INT UNSIGNED".equalsIgnoreCase(TYPE_NAME)) {
						columnSql = columnSql.replaceAll("@@COLUMN_SIZE@@", "").replaceAll("INT UNSIGNED", "INT"+"("+colRS.getString("COLUMN_SIZE")+")"+" UNSIGNED");
					} else {
						columnSql = columnSql.replaceAll("@@COLUMN_SIZE@@", "("+colRS.getString("COLUMN_SIZE")+")");
					}

					columnsSql.append(columnSql + ",");
//    			System.out.println("字段名："+colRS.getString("COLUMN_NAME")+"\t字段注释："+colRS.getString("REMARKS")+"\t字段数据类型："+colRS.getString("TYPE_NAME")+"\t是否允许使用 NULL："+colRS.getString("NULLABLE"));
				}
				
			    ResultSet pkRs = metaDate.getPrimaryKeys(null, null, tableName);
				System.out.println(column_NameComment);
				while(pkRs.next()) {
//					pkSql = pkSqlTemp.replaceAll("@@COLUMN_NAME@@", pkRs.getString(4));
					System.out.println(pkRs.getString(4));
					pkSql = pkSqlTemp.replaceAll("@@COLUMN_NAME@@", column_NameComment.get(pkRs.getString(4)));
				}

				tableSql = tableSqlTemp.replaceAll("@@COLUMNS@@", columnsSql.append(pkSql).toString());

				if (StringUtils.isNotBlank(tableComment)) {
					tableSql = tableSql.replaceAll("@@TABLE_NAME@@", tableComment); // 表名用中文方便建ER
					stmt_yyt_lx_model.executeUpdate(dropTableSqlTemp.replaceAll("@@TABLE_NAME@@", tableComment));
				} else {
					tableSql = tableSql.replaceAll("@@TABLE_NAME@@", tableName); 
					stmt_yyt_lx_model.executeUpdate(dropTableSqlTemp.replaceAll("@@TABLE_NAME@@", tableName));
				}

				System.out.println(tableSql);
				
				stmt_yyt_lx_model.executeUpdate(tableSql);
//				stmt.close();
				column_NameComment.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt_yyt_lx_model != null)
					stmt_yyt_lx_model.close();
			} catch (SQLException se) {}
			try {
				if (conn_yyt_lx_model != null)
					conn_yyt_lx_model.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (stmt_yyt_lx != null)
					stmt_yyt_lx.close();
			} catch (SQLException se) {}
			try {
				if (conn_yyt_lx != null)
					conn_yyt_lx.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
        
    }
}

enum DataSource{
	YYL_LX("yyt_lx"),
	YYT_LX_MODEL("yyt_lx_model");

	private String msg;

	private DataSource(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}