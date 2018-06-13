package com.dsb.freemark.dao.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;

import com.dsb.freemark.annotation.QueryField;
import com.dsb.freemark.dao.BaseDao;
import com.dsb.freemark.tools.Item;
import com.dsb.freemark.tools.MyBeanUtils;
import com.dsb.freemark.tools.Page;
import com.dsb.freemark.tools.QueryObject;
import com.dsb.freemark.tools.ReflectUtil;
import com.dsb.freemark.tools.SQLUtil;
import com.dsb.freemark.tools.Utils;

public class BaseDaoImpl<PK, E> implements BaseDao<PK, E> {

	/**
	 * log4j接口
	 */
	protected Logger logger = Logger.getLogger(getClass());
	protected String sqlFile;

	public BaseDaoImpl() {
		this.entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<E>) parameterizedType[1];
		}
	}

	public String getSqlFile() {
		return sqlFile;
	}

	public void setSqlFile(String sqlFile) {
		this.sqlFile = sqlFile;
	}

	public Class<E> entityClass;

	@Override
	/**
	 * 执行数据更新
	 * 
	 * @param sql
	 * @param connection
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getList(String sql, List<Object> values, Connection connection) {
		PreparedStatement statement = null;
		boolean createFlag = false;
		List<Map<String, Object>> list = null;
		try {
			if (connection == null) {
				connection = getConnection();
				createFlag = true;
			}
			statement = connection.prepareStatement(sql);
			bindParameter(statement, values);
			ResultSet set = statement.executeQuery();
			list = resultSetToList(set);
		} catch (Exception e) {
			logger.error("sql=" + sql, e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (connection != null && createFlag) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return list;
	}

	@Override
	/**
	 * 执行数据查询
	 * 
	 * //2017-03-31 注释分页下标重设 个人使用 他人慎用
	 * 
	 * @param Page
	 *            pager
	 * @param sql
	 * @param connection
	 * @retur List<Map<String, Object>>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPageList(Page pager, String sql, List<Object> values, Connection connection) {
		PreparedStatement statement = null;
		boolean createFlag = false;
		PreparedStatement ps = null;
		PreparedStatement psCount = null;
		List<Map<String, Object>> list = null;
		try {

			if (Utils.isNotEmpty(pager.getOrderBy()) && Utils.isNotEmpty(pager.getOrderType())
					&& sql.indexOf("order by") == -1) {
				sql += " order by " + pager.getOrderBy() + " " + pager.getOrderType();
			}
			if (connection == null) {
				connection = getConnection();
				createFlag = true;
			}

			String countSQL = "select count(*) from (" + sql + ") p";
			// System.out.println(countSQL);
			logger.info(countSQL);
			psCount = connection.prepareStatement(countSQL);
			// statement = connection.prepareStatement(sql);
			bindParameter(psCount, values);
			ResultSet countResult = psCount.executeQuery();
			int totalCount = 0;
			if (countResult.next()) {
				BigDecimal bigDecimal = countResult.getBigDecimal(1);
				totalCount = bigDecimal.intValue();
			}
			int start = pager.getPageSize() * (pager.getPageNumber() - 1);
			// 如果页码超出,重设页码
			if (start > totalCount) {
				/*
				 * int newNumber = totalCount / pager.getPageSize(); if
				 * (totalCount % pager.getPageSize() != 0) { newNumber++; }
				 * pager.setPageNumber(newNumber); start = pager.getPageSize() *
				 * (newNumber - 1);
				 */
				// 2017-03-31 注释 个人使用 他人慎用
				return null;
			}
			if (totalCount > 0) {
				ps = connection.prepareStatement(pageQuerySQL(sql, start, pager.getPageSize()));
				bindParameter(ps, values);
				ResultSet resultSet = ps.executeQuery();
				list = resultSetToList(resultSet);
				pager.setList(list);
			}
			pager.setTotalCount(totalCount);
		} catch (Exception e) {
			logger.error("sql=" + sql, e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (connection != null && createFlag) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return list;
	}

	@Override
	public List<String> getOneColumnStringList(String sql, List<Object> values, Connection conn) {
		PreparedStatement statement = null;
		boolean createFlag = false;
		List<String> list = new ArrayList<String>();
		try {
			if (conn == null) {
				conn = getConnection();
				createFlag = true;
			}
			statement = conn.prepareStatement(sql);
			bindParameter(statement, values);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				list.add(set.getString(1));
			}
		} catch (Exception e) {
			logger.error("sql=" + sql, e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (conn != null && createFlag) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return list;
	}

	public Object getOneColumnObject(String sql, List<Object> values, Connection conn) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Object ret = null;
		boolean createFlag = false;
		try {
			if (conn == null) {
				conn = getConnection();
				createFlag = true;
			}
			statement = conn.prepareStatement(sql);
			bindParameter(statement, values);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				ret = resultSet.getObject(1);
			}
		} catch (SQLException e) {
			logger.error("sql=" + sql, e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (conn != null && createFlag) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Item> getMoreColumnStringList(String sql, Connection conn, Object... values) {
		boolean createFlag = false;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		List<Item> list = new ArrayList<Item>();
		try {
			if (conn == null) {
				conn = getConnection();
				createFlag = true;
			}
			statement = conn.prepareStatement(sql);
			if (values != null && values.length > 0) {
				List<Object> objectList = null;
				if (values.length == 1) {
					if (values[0] != null) {
						if (values[0] instanceof Collection) {
							objectList = (List<Object>) values[0];
						} else {
							objectList = Arrays.asList(values);
						}
					}
				} else {
					objectList = Arrays.asList(values);
				}
				bindParameter(statement, objectList);
			}
			resultSet = statement.executeQuery();
			int fieldCount = resultSet.getMetaData().getColumnCount();
			while (resultSet.next()) {
				Item item = new Item();
				item.setKey(resultSet.getString(1));
				if (fieldCount > 1) {
					item.setValue(resultSet.getString(2));
					if (fieldCount > 2) {
						item.setOther1(resultSet.getString(3));
						if (fieldCount > 3) {
							item.setOther2(resultSet.getString(4));
						}
						if (fieldCount > 4) {
							item.setOther3(resultSet.getString(5));
						}
						if (fieldCount > 5) {
							item.setOther4(resultSet.getString(6));
						}
						if (fieldCount > 6) {
							item.setOther5(resultSet.getString(7));
						}
						if (fieldCount > 7) {
							item.setOther6(resultSet.getString(8));
						}
						if (fieldCount > 8) {
							item.setOther7(resultSet.getString(9));
						}
						if (fieldCount > 9) {
							item.setOther8(resultSet.getString(10));
						}
						if (fieldCount > 10) {
							item.setOther9(resultSet.getString(11));
						}
						if (fieldCount > 11) {
							item.setOther10(resultSet.getString(12));
						}
						if (fieldCount > 12) {
							item.setOther11(resultSet.getString(13));
						}
						if (fieldCount > 13) {
							item.setOther12(resultSet.getString(14));
						}
						if (fieldCount > 14) {
							item.setOther13(resultSet.getString(15));
						}
						if (fieldCount > 15) {
							item.setOther14(resultSet.getString(16));
						}
						if (fieldCount > 16) {
							item.setOther15(resultSet.getString(17));
						}
						if (fieldCount > 17) {
							item.setOther16(resultSet.getString(18));
						}
						if (fieldCount > 18) {
							item.setOther17(resultSet.getString(19));
						}

					}
				}
				list.add(item);
			}
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (conn != null && createFlag) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Page getMoreColumnStringPageList(Page pager, String sql, Connection conn, Object... values) {
		boolean createFlag = false;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		List<Item> list = new ArrayList<Item>();
		try {
			if (Utils.isNotEmpty(pager.getOrderBy()) && Utils.isNotEmpty(pager.getOrderType())
					&& sql.indexOf("order by") == -1) {
				sql += " order by " + pager.getOrderBy() + " " + pager.getOrderType();
			}
			if (conn == null) {
				conn = getConnection();
				createFlag = true;
			}
			int start = pager.getPageSize() * (pager.getPageNumber() - 1);
			statement = conn.prepareStatement(pageQuerySQL(sql, start, pager.getPageSize()));
			if (values != null && values.length > 0) {
				List<Object> objectList = null;
				if (values.length == 1) {
					if (values[0] != null) {
						if (values[0] instanceof Collection) {
							objectList = (List<Object>) values[0];
						} else {
							objectList = Arrays.asList(values);
						}
					}
				} else {
					objectList = Arrays.asList(values);
				}
				bindParameter(statement, objectList);
			}
			resultSet = statement.executeQuery();
			int fieldCount = resultSet.getMetaData().getColumnCount();
			while (resultSet.next()) {
				Item item = new Item();
				item.setKey(resultSet.getString(1));
				if (fieldCount > 1) {
					item.setValue(resultSet.getString(2));
					if (fieldCount > 2) {
						item.setOther1(resultSet.getString(3));
						if (fieldCount > 3) {
							item.setOther2(resultSet.getString(4));
						}
						if (fieldCount > 4) {
							item.setOther3(resultSet.getString(5));
						}
						if (fieldCount > 5) {
							item.setOther4(resultSet.getString(6));
						}
						if (fieldCount > 6) {
							item.setOther5(resultSet.getString(7));
						}
						if (fieldCount > 7) {
							item.setOther6(resultSet.getString(8));
						}
						if (fieldCount > 8) {
							item.setOther7(resultSet.getString(9));
						}
						if (fieldCount > 9) {
							item.setOther8(resultSet.getString(10));
						}
						if (fieldCount > 10) {
							item.setOther9(resultSet.getString(11));
						}
						if (fieldCount > 11) {
							item.setOther10(resultSet.getString(12));
						}
						if (fieldCount > 12) {
							item.setOther11(resultSet.getString(13));
						}
						if (fieldCount > 13) {
							item.setOther12(resultSet.getString(14));
						}
						if (fieldCount > 14) {
							item.setOther13(resultSet.getString(15));
						}
						if (fieldCount > 15) {
							item.setOther14(resultSet.getString(16));
						}
						if (fieldCount > 16) {
							item.setOther15(resultSet.getString(17));
						}
						if (fieldCount > 17) {
							item.setOther16(resultSet.getString(18));
						}
						if (fieldCount > 18) {
							item.setOther17(resultSet.getString(19));
						}

					}
				}
				list.add(item);
			}
			pager.setList(list);
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (conn != null && createFlag) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pager;
	}

	public <T> List<T> getObjectList(String sql, Connection connection, Class<T> clazz, List<Object> values) {
		PreparedStatement statement = null;
		boolean createFlag = false;
		List<T> list = null;
		try {
			if (connection == null) {
				connection = getConnection();
				createFlag = true;
			}
			statement = connection.prepareStatement(sql);
			bindParameter(statement, values);
			ResultSet set = statement.executeQuery();
			list = resultSetToEntityList(set, clazz);
		} catch (Exception e) {
			logger.error("sql=" + sql, e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (connection != null && createFlag) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return list;
	}

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
	public <T> T getEntity(String sql, Connection conn, Class<T> clazz, List<Object> values) {
		List<T> list = getObjectList(sql, conn, clazz, values);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public PK saveEntity(Connection conn, E obj) {
		List<Field> fields = ReflectUtil.getPropertyFields(obj.getClass());
		String fieldNames = "";
		String values = "";
		List<Object> list = new ArrayList<Object>();
		PK idValue = null;

		for (Field field : fields) {
			Transient annotation = field.getAnnotation(Transient.class);
			if (annotation != null) {
				continue;
			}

			String property = field.getName();
			String columnName = null;

			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnName = column.name();
			}

			if (Utils.isEmpty(columnName)) {
				columnName = SQLUtil.prefixPropertyToFieldName(property);
			}
			// 获取属性的值
			Object object = MyBeanUtils.getProperty(obj, property);
			Id id = field.getAnnotation(Id.class);
			if (Utils.isNotEmpty(object) || id != null) {
				fieldNames += columnName + ",";
				values += "?,";

				if (id != null) {
					// id不为空
					if (Utils.isNotEmpty(object)) {
						idValue = (PK) object;
						list.add(idValue);
					} else {
						// 根据id策略生成id
						GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
						GenerationType strategy = generatedValue.strategy();
						if (strategy.equals(GenerationType.SEQUENCE)) {
							SequenceGenerator generator = field.getAnnotation(SequenceGenerator.class);
							String sequenceName = generator.sequenceName();
							idValue = (PK) getSequenceValue(sequenceName, conn);
							list.add(idValue);
						} else if (generatedValue.generator() != null) {
							if (generatedValue.generator().equals("uuid")) {
								idValue = (PK) UUID.randomUUID().toString();
								list.add(idValue);
							} else if (generatedValue.generator().equals("bossid")) {
								GenericGenerator generator = field.getAnnotation(GenericGenerator.class);
								String name = generator.name();
								if (Utils.isNotEmpty(name)) {
									idValue = (PK) getnewbosid(name);
									list.add(idValue);
								} else {
									System.err.println("bossid没有设置！");
								}
							}
						}
					}
				} else {
					list.add(object);
				}
			}
		}

		if (fieldNames.length() > 0) {
			fieldNames = fieldNames.substring(0, fieldNames.length() - 1);
			values = values.substring(0, values.length() - 1);
		}

		String sql = "insert into " + getTableName() + " ( " + fieldNames + " ) values (" + values + ")";
		int count = executeUpdate(sql, list, conn);
		if (count > 0) {
			return idValue;
		} else {
			return null;
		}

	}

	public int saveEntity(Connection conn, List<E> datas) {
		if (datas == null || datas.isEmpty()) {
			return 0;
		}
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();
		List<Field> fields = ReflectUtil.getPropertyFields(datas.get(0).getClass());
		for (E obj : datas) {

			String fieldNames = "";
			String values = "";
			List<Object> list = new ArrayList<Object>();
			PK idValue = null;

			for (Field field : fields) {
				Transient annotation = field.getAnnotation(Transient.class);
				if (annotation != null) {
					continue;
				}

				String property = field.getName();
				String columnName = null;

				Column column = field.getAnnotation(Column.class);
				if (column != null) {
					columnName = column.name();
				}

				if (Utils.isEmpty(columnName)) {
					columnName = SQLUtil.prefixPropertyToFieldName(property);
				}
				// 获取属性的值
				Object object = MyBeanUtils.getProperty(obj, property);
				Id id = field.getAnnotation(Id.class);
				if (Utils.isNotEmpty(object) || id != null) {
					fieldNames += columnName + ",";
					values += "?,";

					if (id != null) {
						// id不为空
						if (Utils.isNotEmpty(object)) {
							idValue = (PK) object;
							list.add(idValue);
						} else {
							// 根据id策略生成id
							GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
							GenerationType strategy = generatedValue.strategy();
							if (strategy.equals(GenerationType.SEQUENCE)) {
								SequenceGenerator generator = field.getAnnotation(SequenceGenerator.class);
								String sequenceName = generator.sequenceName();
								idValue = (PK) getSequenceValue(sequenceName, conn);
								list.add(idValue);
							} else if (generatedValue.generator() != null) {
								if (generatedValue.generator().equals("uuid")) {
									idValue = (PK) UUID.randomUUID().toString();
									list.add(idValue);
								} else if (generatedValue.generator().equals("bossid")) {
									GenericGenerator generator = field.getAnnotation(GenericGenerator.class);
									String name = generator.name();
									if (Utils.isNotEmpty(name)) {
										idValue = (PK) getnewbosid(name);
										list.add(idValue);
									} else {
										System.err.println("bossid没有设置！");
									}
								}
							}
						}
					} else {
						list.add(object);
					}
				}
			}

			if (fieldNames.length() > 0) {
				fieldNames = fieldNames.substring(0, fieldNames.length() - 1);
				values = values.substring(0, values.length() - 1);
			}

			String sql = "insert into " + getTableName() + " ( " + fieldNames + " ) values (" + values + ")";
			sqls.add(sql);
			params.add(list);

		}
		int count = executeBatchUpdate(sqls, params, conn);

		return count;

	}

	/**
	 * 根据id删除对象
	 */
	public int deleteEntity(Connection conn, PK id) {

		Boolean del = true;

		// 判断valid字符存在
		List<Field> fields = ReflectUtil.getPropertyFields(entityClass);
		for (Field field : fields) {
			Transient annotation = field.getAnnotation(Transient.class);
			if (annotation != null) {
				continue;
			}
			String property = field.getName();
			if (property.equals("valid")) {
				del = false;
			}
		}

		String sql = "";
		if (del) {
			sql = "delete from " + getTableName() + " where " + getKeyField() + " = ?";
		} else {
			sql = "UPDATE " + getTableName() + " SET fvalid = 0 where " + getKeyField() + " = ?";
		}

		List<Object> list = new ArrayList<Object>();
		list.add(id);
		return executeUpdate(sql, list, conn);
	}

	@Override
	public Object deleteEntity(String sql, List<Object> values, Connection conn) {
		PreparedStatement statement = null;
		// int resultSet = -1;
		Object ret = null;
		boolean createFlag = false;
		try {
			if (conn == null) {
				conn = getConnection();
				createFlag = true;
			}
			statement = conn.prepareStatement(sql);
			bindParameter(statement, values);
			ret = statement.execute();

		} catch (SQLException e) {
			logger.error("sql=" + sql, e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (conn != null && createFlag) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * 根据id删除对象
	 */
	public int deleteEntity(Connection conn, List<PK> ids) {
		if (ids == null || ids.isEmpty()) {
			return 0;
		}
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();
		for (PK id : ids) {
			List<Object> list = new ArrayList<Object>();
			String sql = "delete from " + getTableName() + " where " + getKeyField() + " = ?";
			list.add(id);
			sqls.add(sql);
			params.add(list);
		}

		return executeBatchUpdate(sqls, params, conn);
	}

	/**
	 * 根据id获取对象
	 */
	public <E> E get(PK id) {
		String sql = "select * from " + getTableName() + " where " + getKeyField() + "= ?";
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(id);

		E entity = (E) getEntity(sql, null, entityClass, list);

		return entity;
	}

	/**
	 * 获得数据连接
	 * 
	 * @return Connection对象
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		// TODO 
		return null;
	}

	/**
	 * 绑定参数
	 * 
	 * @param statement
	 * @param values
	 * @throws SQLException
	 */
	protected void bindParameter(PreparedStatement statement, List<Object> values) throws SQLException {
		if (values != null && values.size() != 0) {
			for (int i = 0; i < values.size(); i++) {
				Object object = values.get(i);
				int index = i + 1;
				if (object instanceof java.sql.Date) {
					statement.setDate(index, (java.sql.Date) object);
				}
				if (object instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) object);
				} else if (object instanceof java.util.Date) {
					Timestamp date = new Timestamp(((java.util.Date) values.get(i)).getTime());
					statement.setTimestamp(index, date);
				} else if (object instanceof String) {
					statement.setString(index, object.toString());
				} else {
					statement.setObject(index, object);
				}
			}
		}

	}

	private List resultSetToList(ResultSet rs) throws Exception {
		if (rs == null)
			return Collections.EMPTY_LIST;
		ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
		int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
		List list = new ArrayList();
		Map rowData = new HashMap();
		while (rs.next()) {
			rowData = new HashMap(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
			System.out.println("list:" + list.toString());
		}
		return list;
	}

	public <T> List<T> resultSetToEntityList(ResultSet set, Class<T> entityClass) throws Exception {
		return resultSetToEntityList(set, entityClass, false);
	}

	/**
	 * 
	 * @param set
	 * @param entityClass
	 * @param filter
	 *            true开启Transient过滤，否则关闭
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> resultSetToEntityList(ResultSet set, Class<T> entityClass, Boolean filter) throws Exception {
		List<T> list = new ArrayList<T>();
		List<Field> fields = ReflectUtil.getPropertyFields(entityClass);
		while (set.next()) {
			T entity = entityClass.newInstance();
			for (Field field : fields) {

				/*
				 * if (filter) { Transient tss =
				 * field.getAnnotation(Transient.class); if (tss != null) {
				 * continue; } }
				 */
				QueryField qf = field.getAnnotation(QueryField.class);
				String name = field.getName();
				// String columnName = SQLUtil.propertyToFieldName(name);
				String columnName = SQLUtil.prefixPropertyToFieldName(name);
				Column column = field.getAnnotation(Column.class);
				if (column != null && Utils.isNotEmpty(column.name())) {
					columnName = column.name();
				}
				try {
					// 忽略最大查询字段
					if (qf == null || qf.resultField()) {
						Class<?> type = field.getType();
						if (type.equals(Date.class) || type.equals(java.sql.Date.class)
								|| type.equals(Timestamp.class)) {
							Timestamp timestamp = set.getTimestamp(columnName);
							// 日期类型不能简单复制
							if (timestamp != null) {
								if (type.equals(Date.class)) {
									Date date = new Date(timestamp.getTime());
									BeanUtils.setProperty(entity, name, date);
								} else if (type.equals(java.sql.Date.class)) {
									java.sql.Date date = new java.sql.Date(timestamp.getTime());
									BeanUtils.setProperty(entity, name, date);
								} else {
									BeanUtils.setProperty(entity, name, timestamp);
								}
							}
						} else {
							String value = set.getString(columnName);
							if (value != null) {
								if (type.equals(String.class)) {
									BeanUtils.setProperty(entity, name, value);
								} else if (type.equals(Long.class)) {
									BeanUtils.setProperty(entity, name, Long.valueOf(value));
								} else if (type.equals(Integer.class)) {
									BeanUtils.setProperty(entity, name, Integer.valueOf(value));
								} else if (type.equals(Double.class)) {
									BeanUtils.setProperty(entity, name, Double.valueOf(value));
								} else if (type.equals(Float.class)) {
									BeanUtils.setProperty(entity, name, Float.valueOf(value));
								} else if (type.equals(Boolean.class)) {
									BeanUtils.setProperty(entity, name, Boolean.valueOf(value));
								}
							}
						}

					}

				} catch (Exception e) {
					logger.debug("column:" + columnName, e);
				}
			}
			list.add(entity);
		}

		return list;
	}

	protected Long getSequenceValue(String sequenceName, Connection conn) {
		String temp = "select " + sequenceName + ".nextval from dual";
		BigDecimal object = (BigDecimal) getOneColumnObject(temp, null, conn);
		return object.longValue();
	}

	public String getnewbosid(String bosType) {
		String sql = "select newbosid('" + bosType + "') from dual";
		String value = (String) getOneColumnObject(sql, null, null);
		return value;
	}

	public String getTableName() {
		Table table = entityClass.getAnnotation(Table.class);
		String tableName = SQLUtil.classTatbleName(entityClass.getSimpleName());
		if (table != null && Utils.isNotEmpty(table.name())) {
			tableName = table.name();
		}
		return tableName;
	}

	public int executeUpdate(String sql, List<Object> values, Connection connection) {
		PreparedStatement statement = null;
		boolean createFlag = false;
		int updateCount = 0;
		try {
			if (connection == null) {
				connection = getConnection();
				createFlag = true;
			}
			statement = connection.prepareStatement(sql);
			bindParameter(statement, values);
			updateCount = statement.executeUpdate();
			// logger.info("执行sql:" + sql + ";updateCount=" + updateCount);
		} catch (SQLException e) {
			String msg = e.getMessage();
			if (msg != null && msg.length() > 1)
				msg = msg.substring(0, msg.length() - 1);// 去掉换行符
			logger.error("sql出错: " + sql + "\r\n" + SQLException.class.getName() + ": " + msg);
			// 为了使修正事务问题，而不大面积修改代码而加入的一个人为异常；日后框架整理时需要整改
			Long.valueOf("实际异常信息: " + SQLException.class.getName() + ": " + msg);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (connection != null && createFlag) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}
		return updateCount;
	}

	public int executeBatchUpdate(List<String> sqls, List<List<Object>> values, Connection connection) {
		PreparedStatement statement = null;
		boolean createFlag = false;
		int sum = 0;
		try {
			if (connection == null) {
				connection = getConnection();
				createFlag = true;
			}

			// 开始封装批量执行语句
			if (sqls.size() != values.size()) {
				return 0;
			}
			statement = connection.prepareStatement(sqls.get(0));
			for (int i = 0; i < sqls.size(); i++) {
				// String sql = sqls.get(i);

				bindParameter(statement, values.get(i));
				statement.addBatch();
			}

			int[] updateCount = statement.executeBatch();
			for (int j : updateCount) {
				sum += j;
			}
			logger.info("执行sql:" + sqls + ";updateCount=" + updateCount);
		} catch (SQLException e) {
			String msg = e.getMessage();
			if (msg != null && msg.length() > 1)
				msg = msg.substring(0, msg.length() - 1);// 去掉换行符
			logger.error("sql出错: " + sqls + "\r\n" + SQLException.class.getName() + ": " + msg);
			// 为了使修正事务问题，而不大面积修改代码而加入的一个人为异常；日后框架整理时需要整改
			Long.valueOf("实际异常信息: " + SQLException.class.getName() + ": " + msg);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (connection != null && createFlag) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}

		return sum;
	}

	public String getKeyField() {
		List<Field> fields = ReflectUtil.getPropertyFields(entityClass);
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				Column column = field.getAnnotation(Column.class);
				if (column != null) {
					String name = column.name();
					if (Utils.isNotEmpty(name)) {
						return name;
					}
				}
				return SQLUtil.prefixPropertyToFieldName(field.getName());
			}
		}
//		Assert.notNull(null, "找不到主键字段");
		return null;
	}

	public String buildSQL() {
		String sql = "select * from " + getTableName();
		if (sqlFile != null) {
			try {
				sql = "select * from ( " + readSQL(sqlFile) + " )";
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return sql;
	}

	public String readSQL(String sqlFile) throws IOException {
		String file = getClass().getResource(sqlFile).getFile();
		file = file.replace("%20", " ");
		return FileUtils.readFileToString(new File(file));
	}

	@Override
	public int updateEntity(Connection conn, E obj) {
		List<Field> fields = ReflectUtil.getPropertyFields(entityClass);
		String sets = "";
		String where = null;
		Object idValue = null;
		List<Object> list = new ArrayList<Object>();
		for (Field field : fields) {
			Transient annotation = field.getAnnotation(Transient.class);
			if (annotation != null) {
				continue;
			}
			String property = field.getName();
			String columnName = null;
			Column column = field.getAnnotation(Column.class);
			Id id = field.getAnnotation(Id.class);

			if (column != null) {
				columnName = column.name();
				if (!column.updatable() && id == null) {
					continue;
				}
			}

			if (Utils.isEmpty(columnName)) {
				columnName = SQLUtil.prefixPropertyToFieldName(property);
			}
			Object object = MyBeanUtils.getProperty(obj, property);
			if (id == null) {
				sets += columnName + " = ?,";
				list.add(object);
			} else {
//				Assert.notNull(object, "id不能为空!");
				where = columnName + " = ?";
				idValue = object;
			}
		}

		list.add(idValue);
		if (sets.length() > 0) {
			sets = sets.substring(0, sets.length() - 1);
		}
//		Assert.notNull(where, "id不能为空!");
		String sql = "update " + getTableName() + " set " + sets + " where " + where;
		return executeUpdate(sql, list, conn);
	}

	@Override
	public Object updateEntity(String sql, List<Object> values, Connection conn) {
		PreparedStatement statement = null;
		// int resultSet = -1;
		Object ret = null;
		boolean createFlag = false;
		try {
			if (conn == null) {
				conn = getConnection();
				createFlag = true;
			}
			statement = conn.prepareStatement(sql);
			bindParameter(statement, values);
			ret = statement.executeUpdate();

		} catch (SQLException e) {
			logger.error("sql=" + sql, e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (conn != null && createFlag) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public int updateBatchEntity(Connection conn, List<E> datas) {
		if (datas == null || datas.isEmpty()) {
			return 0;
		}
		List<Field> fields = ReflectUtil.getPropertyFields(entityClass);
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();
		for (E obj : datas) {
			String sets = "";
			String where = null;
			Object idValue = null;
			List<Object> list = new ArrayList<Object>();
			for (Field field : fields) {
				Transient annotation = field.getAnnotation(Transient.class);
				if (annotation != null) {
					continue;
				}
				String property = field.getName();
				String columnName = null;
				Column column = field.getAnnotation(Column.class);
				Id id = field.getAnnotation(Id.class);

				if (column != null) {
					columnName = column.name();
					if (!column.updatable() && id == null) {
						continue;
					}
				}

				if (Utils.isEmpty(columnName)) {
					columnName = SQLUtil.prefixPropertyToFieldName(property);
				}
				Object object = MyBeanUtils.getProperty(obj, property);
				if (id == null) {
					sets += columnName + " = ?,";
					list.add(object);
				} else {
//					Assert.notNull(object, "id不能为空!");
					where = columnName + " = ?";
					idValue = object;
				}
			}

			list.add(idValue);
			if (sets.length() > 0) {
				sets = sets.substring(0, sets.length() - 1);
			}
//			Assert.notNull(where, "id不能为空!");
			String sql = "update " + getTableName() + " set " + sets + " where " + where;
			sqls.add(sql);
			params.add(list);

		}

		return executeBatchUpdate(sqls, params, conn);
	}

	@Override
	public Page pageQuery(Page pager, String sql, List<Object> values, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		PreparedStatement psCount = null;
		boolean createFlag = false;
		try {
			if (Utils.isNotEmpty(pager.getOrderBy()) && Utils.isNotEmpty(pager.getOrderType())
					&& sql.indexOf("order by") == -1) {
				sql += " order by " + pager.getOrderBy() + " " + pager.getOrderType();
			}
			if (conn == null) {
				conn = getConnection();
				createFlag = true;
			}
			String countSQL = "select count(*) from (" + sql + ") p";
			// System.out.println(countSQL);
			logger.info(countSQL);

			// logger.info("统计数量开始执行"+DateTimeUtils.getSystemDate());
			psCount = conn.prepareStatement(countSQL);
			bindParameter(psCount, values);
			ResultSet countResult = psCount.executeQuery();
			int totalCount = 0;
			if (countResult.next()) {
				BigDecimal bigDecimal = countResult.getBigDecimal(1);
				totalCount = bigDecimal.intValue();
			}
			int start = pager.getPageSize() * (pager.getPageNumber() - 1);
			// 如果页码超出,重设页码
			if (start > totalCount) {
				/*
				 * int newNumber = totalCount / pager.getPageSize(); if
				 * (totalCount % pager.getPageSize() != 0) { newNumber++; }
				 * pager.setPageNumber(newNumber); start = pager.getPageSize() *
				 * (newNumber - 1);
				 */
				return null;
			}
			if (totalCount > 0) {
				ps = conn.prepareStatement(pageQuerySQL(sql, start, pager.getPageSize()));
				bindParameter(ps, values);
				ResultSet resultSet = ps.executeQuery();
				List<E> list = resultSetToEntityList(resultSet, entityClass, false);
				pager.setList(list);
			}
			pager.setTotalCount(totalCount);
		} catch (Exception e) {
			logger.error("sql=" + sql, e);
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (psCount != null) {
				psCount.close();
			}
			if (conn != null && createFlag) {
				conn.close();
			}
		}
		return pager;
	}

	protected String pageQuerySQL(String sql, int start, int size) {
		String pageSql = "select * from (\r\nselect a.*,@rowno:=@rowno+1 as rn from ( " + sql
				+ ") a, (select @rowno:=0) trw \r\n) a where a.rn<=" + (start + size);
		pageSql = "select b.* from ( " + pageSql + " ) b where b.rn >" + start;
		return pageSql;
	}

	public Page pageObjectQuery(Page pager, String sql, List<Object> values, Connection conn, Class entityClass)
			throws SQLException {
		PreparedStatement ps = null;
		PreparedStatement psCount = null;
		boolean createFlag = false;
		try {
			if (Utils.isNotEmpty(pager.getOrderBy()) && Utils.isNotEmpty(pager.getOrderType())
					&& sql.indexOf("order by") == -1) {
				sql += " order by " + pager.getOrderBy() + " " + pager.getOrderType();
			}
			if (conn == null) {
				conn = getConnection();
				createFlag = true;
			}
			String countSQL = "select count(*) from (" + sql + ") cp";
			// System.out.println(countSQL);
			logger.info(countSQL);

			// logger.info("统计数量开始执行"+DateTimeUtils.getSystemDate());
			psCount = conn.prepareStatement(countSQL);
			bindParameter(psCount, values);
			ResultSet countResult = psCount.executeQuery();
			int totalCount = 0;
			if (countResult.next()) {
				BigDecimal bigDecimal = countResult.getBigDecimal(1);
				totalCount = bigDecimal.intValue();
			}
			int start = pager.getPageSize() * (pager.getPageNumber() - 1);
			// 如果页码超出,重设页码
			if (start > totalCount) {
				/*
				 * int newNumber = totalCount / pager.getPageSize(); if
				 * (totalCount % pager.getPageSize() != 0) { newNumber++; }
				 * pager.setPageNumber(newNumber); start = pager.getPageSize() *
				 * (newNumber - 1);
				 */
				return null;
			}
			if (totalCount > 0) {
				ps = conn.prepareStatement(pageQuerySQL(sql, start, pager.getPageSize()));
				bindParameter(ps, values);
				ResultSet resultSet = ps.executeQuery();
				List list = resultSetToEntityList(resultSet, entityClass, false);
				pager.setList(list);
			}
			pager.setTotalCount(totalCount);
		} catch (Exception e) {
			logger.error("sql=" + sql, e);
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (psCount != null) {
				psCount.close();
			}
			if (conn != null && createFlag) {
				conn.close();
			}
		}
		return pager;
	}

	public Page list(Page page, E obj) {
		// 1.拼装sql语句
		String sql = getSelectSql(page, obj);

		StringBuffer andWhere = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		dataSearch(obj, values, andWhere);
		/*
		 * //List<Object> values = new ArrayList<Object>(); //dataSearch(obj,
		 * values, andWhere);
		 */ /*
			 * List<Field> fields =
			 * ReflectUtil.getPropertyFields(obj.getClass()); List<Object>
			 * values = new ArrayList<Object>(); for (Field field : fields) {
			 * QueryField annotation = field.getAnnotation(QueryField.class); if
			 * (annotation == null) { continue; }
			 * 
			 * String property = field.getName(); String columnName = null;
			 * 
			 * Column column = field.getAnnotation(Column.class); if (column !=
			 * null) { columnName = column.name(); }
			 * 
			 * if (Utils.isEmpty(columnName)) { columnName =
			 * SQLUtil.prefixPropertyToFieldName(property); }
			 * 
			 * String targetColumn = annotation.targetColumn();
			 * 
			 * if(Utils.isNotEmpty(targetColumn)){ columnName = targetColumn; }
			 * 
			 * // 获取属性的值 Object object = MyBeanUtils.getProperty(obj, property);
			 * if (Utils.isNotEmpty(object)) {
			 * if(annotation.queryType().name().equals("EQ")){ andWhere +=
			 * " and "+columnName + " = ?"; values.add(object); }
			 * if(annotation.queryType().name().equals("LIKE")){ andWhere +=
			 * " and "+columnName + " like ?"; values.add("'%"+object+"%'"); }
			 * if(annotation.queryType().name().equals("GT")){ andWhere +=
			 * " and "+columnName + " >= ?"; values.add(object); }
			 * if(annotation.queryType().name().equals("ST")){ andWhere +=
			 * " and "+columnName + " <= ? "; values.add(object); }
			 * 
			 * } }
			 */

		if (andWhere.length() > 0) {
			sql = sql + andWhere;
		}
		try {
			page.setQueryObject(new QueryObject());
			page.getQueryObject().setSql(sql);
			page.getQueryObject().setValues(values);
			page = pageQuery(page, sql, values, null);
		} catch (SQLException e) {
			logger.error("sql+" + sql + "\n" + e.getMessage());
		}

		return page;

	}

	/**
	 * 拼装查询sql
	 * 
	 * @param page
	 * @param obj
	 * @return
	 */
	protected String getSelectSql(Page page, E obj) {
		return "select * from " + getTableName() + " where 1=1";
	}

	/**
	 * 数据搜索
	 * 
	 * @param obj
	 * @param values
	 * @param andWhere
	 * @return
	 */
	public Boolean dataSearch(E obj, List<Object> values, StringBuffer andWhere) {

		List<Field> fields = ReflectUtil.getPropertyFields(obj.getClass());
		for (Field field : fields) {
			QueryField annotation = field.getAnnotation(QueryField.class);
			if (annotation == null) {
				continue;
			}

			String property = field.getName();
			String columnName = null;

			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnName = column.name();
			}

			if (Utils.isEmpty(columnName)) {
				columnName = SQLUtil.prefixPropertyToFieldName(property);
			}

			String targetColumn = annotation.targetColumn();

			if (Utils.isNotEmpty(targetColumn)) {
				columnName = targetColumn;
			}

			// 获取属性的值
			Object object = MyBeanUtils.getProperty(obj, property);
			if (Utils.isNotEmpty(object)) {
				if (annotation.queryType().name().equals("EQ")) {
					andWhere.append(" and " + columnName + " = ?");

					if (values != null) {
						values.add(object);
					}
				} else if (annotation.queryType().name().equals("GT")) {
					andWhere.append(" and " + columnName + " >= ?");
					if (values != null) {
						values.add(object);
					}
				} else if (annotation.queryType().name().equals("ST")) {
					andWhere.append(" and " + columnName + " <= ? ");
					if (values != null) {
						values.add(object);
					}
				} else if (annotation.queryType().name().equals("LIKE")) {
					andWhere.append(" and " + columnName + " like ?");
					if (values != null) {
						values.add(object);
					}
				} else if (annotation.queryType().name().equals("LIKE_LEFT")) {
					if (values != null) {
						andWhere.append(" and " + columnName + " like '%" + object + "'");
					}
				} else if (annotation.queryType().name().equals("LIKE_RIGHT")) {
					if (values != null) {
						andWhere.append(" and " + columnName + " like '" + object + "%'");
					}
				} else if (annotation.queryType().name().equals("LEFT_LIKE_RIGHT")) {
					if (values != null) {
						andWhere.append(" and " + columnName + " like '%" + object + "%'");
					}
				}else if (annotation.queryType().name().equals("NEQ")) {
					if (values != null) {
						andWhere.append(" and " + columnName + " <> '" + object + "'");
					}
				}

			}
		}

		return null;

	}

}
