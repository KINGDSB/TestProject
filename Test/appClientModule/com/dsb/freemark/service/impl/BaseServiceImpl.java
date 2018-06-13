package com.dsb.freemark.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dsb.freemark.dao.BaseDao;
import com.dsb.freemark.service.BaseService;
import com.dsb.freemark.service.BusinessService;
import com.dsb.freemark.tools.Item;
import com.dsb.freemark.tools.Page;

public class BaseServiceImpl<PK, E> implements BaseService<PK, E>, BusinessService<PK, E> {

	protected BaseDao<PK, E> baseDao;

	public void setBaseDao(BaseDao<PK, E> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Map<String, Object>> getList(String sql, List<Object> values, Connection conn) {
		return baseDao.getList(sql, values, conn);
	}

	@Override
	public List<Map<String, Object>> getPageList(Page pager, String sql, List<Object> values, Connection conn) {
		return baseDao.getPageList(pager, sql, values, conn);
	}

	@Override
	public List<String> getOneColumnStringList(String sql, List<Object> values, Connection conn) {
		return baseDao.getOneColumnStringList(sql, values, conn);
	}

	@Override
	public Object getOneColumnObject(String sql, List<Object> values, Connection conn) {
		return baseDao.getOneColumnObject(sql, values, conn);
	}

	@Override
	public List<Item> getMoreColumnStringList(String sql, Connection conn, Object... values) {
		return baseDao.getMoreColumnStringList(sql, conn, values);
	}

	public Page getMoreColumnStringPageList(Page pager, String sql, Connection conn, Object... values) {
		return baseDao.getMoreColumnStringPageList(pager, sql, conn, values);
	}

	@Override
	public <T> List<T> getObjectList(String sql, Connection conn, Class<T> clazz, List<Object> values) {
		// TODO Auto-generated method stub
		return baseDao.getObjectList(sql, conn, clazz, values);
	}

	@Override
	public <T> T getEntity(String sql, Connection conn, Class<T> clazz, List<Object> values) {
		// TODO Auto-generated method stub
		return (T) baseDao.getEntity(sql, conn, clazz, values);
	}

	@Override
	public PK saveEntity(Connection conn, E obj) {
		// TODO Auto-generated method stub
		return (PK) baseDao.saveEntity(conn, obj);
	}

	@Override
	public int saveEntity(Connection conn, List<E> list) {
		// TODO Auto-generated method stub
		return baseDao.saveEntity(conn, list);
	}

	@Override
	public int deleteEntity(Connection conn, PK id) {
		// TODO Auto-generated method stub
		return baseDao.deleteEntity(conn, id);
	}

	@Override
	public int deleteEntity(Connection conn, List<PK> ids) {
		// TODO Auto-generated method stub
		return baseDao.deleteEntity(conn, ids);
	}

	@Override
	public int deleteBatchEntity(List<PK> ids) {
		// TODO Auto-generated method stub
		return baseDao.deleteEntity(null, ids);
	}

	@Override
	public int updateEntity(Connection conn, E obj) {
		// TODO Auto-generated method stub
		return baseDao.updateEntity(conn, obj);
	}

	@Override
	public int updateBatchEntity(Connection conn, List<E> datas) {
		// TODO Auto-generated method stub
		return baseDao.updateBatchEntity(conn, datas);
	}

	@Override
	public int updateBatchEntity(List<E> datas) {
		// TODO Auto-generated method stub
		return baseDao.updateBatchEntity(null, datas);
	}

	@Override
	public <E> E get(PK id) {
		// TODO Auto-generated method stub
		return (E) baseDao.get(id);
	}

	@Override
	public PK save(E obj) {
		// TODO Auto-generated method stub
		return (PK) baseDao.saveEntity(null, obj);
	}

	@Override
	public int save(List<E> list) {
		// TODO Auto-generated method stub
		return baseDao.saveEntity(null, list);
	}

	@Override
	public int delete(PK id) {
		// TODO Auto-generated method stub
		return baseDao.deleteEntity(null, id);
	}

	@Override
	public int update(E obj) {
		// TODO Auto-generated method stub
		return baseDao.updateEntity(null, obj);
	}

	@Override
	public Page list(Page page, E obj) {

		return baseDao.list(page, obj);

	}

	@Override
	public Page pageQuery(Page pager, String sql, List<Object> values, Connection conn) throws SQLException {
		return baseDao.pageQuery(pager, sql, values, conn);
	}

	public Page pageObjectQuery(Page pager, String sql, List<Object> values, Connection conn, Class entityClass)
			throws SQLException {
		return baseDao.pageObjectQuery(pager, sql, values, conn, entityClass);
	}

	@Override
	public Object updateEntity(String sql, List<Object> values, Connection conn) {
		// TODO Auto-generated method stub
		return baseDao.updateEntity(sql, values, conn);
	}

	@Override
	public Object deleteEntity(String sql, List<Object> values, Connection conn) {
		// TODO Auto-generated method stub
		return baseDao.deleteEntity(sql, values, conn);
	}

}
