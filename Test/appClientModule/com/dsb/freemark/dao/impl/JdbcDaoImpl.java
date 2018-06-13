package com.dsb.freemark.dao.impl;

import java.lang.reflect.Field;
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

import javax.persistence.Column;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.dsb.freemark.annotation.QueryField;
import com.dsb.freemark.dao.JdbcDao;
import com.dsb.freemark.tools.Item;
import com.dsb.freemark.tools.ReflectUtil;
import com.dsb.freemark.tools.SQLUtil;
import com.dsb.freemark.tools.Utils;

public class JdbcDaoImpl implements JdbcDao {

	/**
	 * log4j�ӿ�
	 */
	protected Logger logger = Logger.getLogger(getClass());

	@Override
	/**
	 * ִ�����ݸ���
	 * 
	 * @param sql
	 * @param connection
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getList(String sql, List<Object> values, Connection connection) {
		PreparedStatement statement = null;
		boolean createFlag = false;
		List list = null;
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
	public List<String> getStringList(String sql, List<Object> values, Connection conn) {
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

	@SuppressWarnings("unchecked")
	public List<Item> getItemList(String sql, Connection conn, Object... values) {
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
	 * ͨ��SQL�������ݲ�ѯ�����ص�������ʵ��
	 * 
	 * @param sql
	 * @param values
	 * @param clazz
	 *            �ڴ����صĶ�������
	 * @param conn
	 *            ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 */
	public <T> T getEntity(String sql, Connection conn, Class<T> clazz, List<Object> values) {
		List<T> list = getObjectList(sql, conn, clazz, values);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * �����������
	 * 
	 * @return Connection����
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		// TODO 
		return null;
	}

	/**
	 * �󶨲���
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
		ResultSetMetaData md = rs.getMetaData(); // �õ������(rs)�Ľṹ��Ϣ�������ֶ������ֶ�����
		int columnCount = md.getColumnCount(); // ���ش� ResultSet �����е�����
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
		List<T> list = new ArrayList<T>();
		List<Field> fields = ReflectUtil.getPropertyFields(entityClass);
		while (set.next()) {
			T entity = entityClass.newInstance();
			for (Field field : fields) {
				QueryField qf = field.getAnnotation(QueryField.class);
				String name = field.getName();
				String columnName = SQLUtil.propertyToFieldName(name);
				Column column = field.getAnnotation(Column.class);
				if (column != null && Utils.isNotEmpty(column.name())) {
					columnName = column.name();
				}
				try {
					// ��������ѯ�ֶ�
					if (qf == null || qf.resultField()) {
						Class<?> type = field.getType();
						if (type.equals(Date.class) || type.equals(java.sql.Date.class)
								|| type.equals(Timestamp.class)) {
							Timestamp timestamp = set.getTimestamp(columnName);
							// �������Ͳ��ܼ򵥸���
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

}
