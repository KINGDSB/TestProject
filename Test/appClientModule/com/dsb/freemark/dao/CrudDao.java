package com.dsb.freemark.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dsb.freemark.tools.Item;
import com.dsb.freemark.tools.Page;

public interface CrudDao<T, PK extends Serializable> {

	/**
	 * ͨ��ID������¼
	 * 
	 * @param id
	 * @return
	 */
	public T get(PK id);

	/**
	 * ͨ��ID������¼
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> get(PK[] id);

	/**
	 * �����¼
	 * 
	 * @param entity
	 * @return
	 */
	public PK save(T entity);

	/**
	 * ͨ��ʵ����ɾ����¼
	 * 
	 * @param entity
	 */
	public int update(T entity);

	/**
	 * ͨ��ID��ɾ����¼
	 * 
	 * @param id
	 */
	public int delete(PK id);

	/**
	 * ͨ��ID������ɾ����¼
	 * 
	 * @param id����
	 */
	public int delete(PK[] ids);

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param pager
	 *            ��ҳ����
	 * @param entity
	 *            ʵ�������
	 * @return
	 * @throws SQLException
	 */
	public Page pageQuery(Page pager, T entity) throws Exception;

	@SuppressWarnings("unchecked")
	public List<Item> getItemList(String sql, Object... values);

	/**
	 * �����µ�ID��
	 * 
	 * @return
	 */
	public String getnewbosid();

	/**
	 * �����µ�ID��
	 * 
	 * @return
	 */
	public String getnewbosid(String bosType);

	/**
	 * ���ı��ֶε�ֵ
	 * 
	 * @param tableName
	 *            ����
	 * @param itemList
	 *            Colunm�б�name��������column:�е�ֵ��
	 *            type�������ͣ�Ĭ���ַ�����String��null����ʱ��(time)�����֣�int����
	 *            format:��ʽ����:yyyy-mm-dd hh24:mi:ss��
	 * @param whereColumn
	 *            ����������ѯ����
	 * @param whereValue
	 *            ����������ѯ��ֵ
	 * @param conn
	 *            ����
	 * @return
	 */
	public int updateTableColumn(String tableName, List<Item> itemList, String whereColumn, String whereValue,
			Connection conn);

}
