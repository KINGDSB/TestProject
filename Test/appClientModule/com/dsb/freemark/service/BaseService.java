package com.dsb.freemark.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dsb.freemark.tools.Item;
import com.dsb.freemark.tools.Page;

public interface BaseService<PK, E> {

	/**
	 * 通过SQL进行数据查询
	 * 
	 * @param sql
	 * @param values
	 * @param conn
	 *            数据库连接。如果为空，则在方法内部自动创建并关闭连接，这时方法具备独立的事务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getList(String sql, List<Object> values, Connection conn);
	
	/**
	 * 通过SQL进行数据查询
	 * 
	 * @param sql
	 * @param values
	 * @param conn
	 * @param pager
	 *            数据库连接。如果为空，则在方法内部自动创建并关闭连接，这时方法具备独立的事务
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPageList(Page pager, String sql, List<Object> values, Connection conn);

	/**
	 * 通过SQL进行数据查询，返回String列表，适应只返回一列的情况
	 * 
	 * @param sql
	 * @param values
	 * @param conn
	 *            数据库连接。如果为空，则在方法内部自动创建并关闭连接，这时方法具备独立的事务
	 * @return
	 */
	public List<String> getOneColumnStringList(String sql, List<Object> values, Connection conn);

	/**
	 * 通过SQL进行数据查询，返回String列表，适应只返回一列的情况
	 * 
	 * @param sql
	 * @param values
	 * @param conn
	 *            数据库连接。如果为空，则在方法内部自动创建并关闭连接，这时方法具备独立的事务
	 * @return
	 */
	public Object getOneColumnObject(String sql, List<Object> values, Connection conn);

	/**
	 * 通过SQL进行数据查询，返回Item列表，适应返回两个(或少数个)列的情况
	 * 
	 * @param sql
	 * @param values
	 * @param conn
	 *            数据库连接。如果为空，则在方法内部自动创建并关闭连接，这时方法具备独立的事务
	 * @return
	 */
	public List<Item> getMoreColumnStringList(String sql, Connection conn, Object... values);

	
	/**
	 * 通过SQL进行数据查询，返回Item列表，适应返回两个(或少数个)列的情况
	 * @param sql
	 * @param values
	 * @param conn 数据库连接。如果为空，则在方法内部自动创建并关闭连接，这时方法具备独立的事务
	 * @return
	 */
	public Page getMoreColumnStringPageList(Page pager,String sql,Connection conn, Object...values);

	
	/**
	 * 通过SQL进行数据查询，返回对象列表
	 * 
	 * @param sql
	 * @param values
	 *            参数列表
	 * @param clazz
	 *            期待返回的对象类型
	 * @param conn
	 *            数据库连接。如果为空，则在方法内部自动创建并关闭连接，这时方法具备独立的事务
	 * @return
	 */
	public <T> List<T> getObjectList(String sql, Connection conn, Class<T> clazz, List<Object> values);

	/**
	 * 通过SQL进行数据查询，返回单个对象实体
	 * 
	 * @param sql
	 * @param values
	 * @param clazz
	 *            期待返回的对象类型
	 * @param conn
	 *            数据库连接。如果为空，则在方法内部自动创建并关闭连接，这时方法具备独立的事务
	 * @return
	 */
	public <T> T getEntity(String sql, Connection conn, Class<T> clazz, List<Object> values);

	// 增删改查接口
	public PK saveEntity(Connection conn, E obj);

	public int saveEntity(Connection conn, List<E> list);

	public int deleteEntity(Connection conn, PK id);
	public Object deleteEntity(String sql, List<Object> values, Connection conn);

	public int updateEntity(Connection conn, E obj);

	public <E> E get(PK id);

	public Page list(Page page, E obj);

	// 增删改查接口
	public PK save(E obj);

	public int save(List<E> list);

	public int delete(PK id);

	public int deleteEntity(Connection conn, List<PK> ids);

	public int deleteBatchEntity(List<PK> ids);

	public int update(E obj);

	public int updateBatchEntity(Connection conn, List<E> list);

	public Object updateEntity(String sql, List<Object> values, Connection conn);

	public int updateBatchEntity(List<E> list);

	public Page pageQuery(Page pager, String sql, List<Object> values, Connection conn) throws SQLException;

	public Page pageObjectQuery(Page pager, String sql, List<Object> values, Connection conn, Class entityClass) throws SQLException;
}
