package com.dsb.freemark.dao;

import java.sql.Connection;
import java.util.List;

import com.dsb.freemark.tools.Item;


public interface JdbcDao {
	
	/**
	 * ͨ��SQL�������ݲ�ѯ
	 * @param sql
	 * @param values
	 * @param conn ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getList(String sql, List<Object> values,Connection conn);
	
	
	/**
	 * ͨ��SQL�������ݲ�ѯ������String�б���Ӧֻ����һ�е����
	 * @param sql
	 * @param values
	 * @param conn ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 */
	public List<String> getStringList(String sql, List<Object> values,Connection conn);
	
	
	/**
	 * ͨ��SQL�������ݲ�ѯ������Item�б���Ӧ��������(��������)�е����
	 * @param sql
	 * @param values
	 * @param conn ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 */
	public List<Item> getItemList(String sql,Connection conn, Object...values);

	
	/**
	 * ͨ��SQL�������ݲ�ѯ�����ض����б�
	 * @param sql
	 * @param values
	 * @param clazz �ڴ����صĶ������� 
	 * @param conn ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 */
	public <T> List<T> getObjectList(String sql,Connection conn, Class<T> clazz,List<Object> values);
	
	/**
	 * ͨ��SQL�������ݲ�ѯ�����ص�������ʵ��
	 * @param sql
	 * @param values
	 * @param clazz �ڴ����صĶ�������
	 * @param conn ���ݿ����ӡ����Ϊ�գ����ڷ����ڲ��Զ��������ر����ӣ���ʱ�����߱�����������
	 * @return
	 */
	public <T> T getEntity(String sql,Connection conn, Class<T> clazz,List<Object> values);
}
