package com.dsb.freemark.service;

import java.util.List;

import com.dsb.freemark.tools.Page;

public interface BusinessService<PK, E> {

	// ��ɾ�Ĳ�ӿ�
	public PK save(E obj);
	public int save(List<E> list);

	public int delete(PK id);

	public int update( E obj);
	
	public Page list(Page page,E obj);

}
