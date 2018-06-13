package com.dsb.freemark.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dsb.freemark.tools.Page;

public interface JdbcCrudDao<T, PK extends Serializable> extends JdbcDao, CrudDao<T, PK> {

	/**
	 * ͨ��ID������¼
	 * 
	 * @param id
	 * @param conn
	 *            ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 */
	public T get(PK id, Connection conn);

	/**
	 * �����¼
	 * 
	 * @param entity
	 * @param conn
	 * 
	 *            ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 */
	public PK save(T entity, Connection conn);

	/**
	 * ͨ��ʵ����ɾ����¼
	 * 
	 * @param entity
	 * @param conn
	 *            ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 */
	public int update(T entity, Connection conn);

	/**
	 * ͨ��ID��ɾ����¼
	 * 
	 * @param id
	 * @param conn
	 *            ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 */
	public int delete(PK id, Connection conn);

	/**
	 * ͨ��ID����ɾ����¼
	 * 
	 * @param ids
	 * @param conn
	 *            ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 */
	public int delete(PK[] ids, Connection conn);

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param pager
	 *            ��ҳ����
	 * @param entity
	 *            ʵ�������
	 * @param conn
	 *            ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 * @throws SQLException
	 */
	public Page pageQuery(Page pager, T entity, Connection conn) throws SQLException;

	/**
	 * ��ѯ������ʵ���б��������ҳ�����ͬʱ������¼������
	 * 
	 * @param sql
	 * @param values
	 * @param connection
	 * @return
	 */
	public List<T> getEntityList(String sql, List<Object> values, Connection connection);

	/**
	 * ��ѯ������ʵ���б��������ҳ�����ͬʱ������¼������
	 * 
	 * @param sql
	 * @param values
	 * @param connection
	 * @return
	 */
	public List<T> getEntityList(T entity, Connection conn);
}
