package com.dsb.freemark.tools;

import java.util.List;

public class Page {
	
	/**
	 * 排序方式
	 *
	 */
	public static enum OrderType{
		asc, desc
	}
	
	public static final Integer MAX_PAGE_SIZE = 100;

	/**
	 * 当前页码
	 */
	private Integer pageNumber = 1;
	
	/**
	 * 每页记录数
	 */
	private Integer pageSize = 10000;
	
	/**
	 * 总记录数
	 */
	private Integer totalCount = 0;
	
	/**
	 * 总页数
	 */
	private Integer pageCount = 0;
	
	
	/**
	 * 排序字段
	 */
	private String orderBy;
	
	/**
	 * 排序方式
	 */
	private OrderType orderType = OrderType.desc;
	
	private QueryObject queryObject;
	/**
	 * 随机数
	 */
	private String randomNumer;
	/**
	 * 数据List
	 */
	@SuppressWarnings("unchecked")
	private List list;
	
	public Page() {
		if(null==randomNumer){
		    this.randomNumer = Utils.getRandomString();
		}
	}
	
	
	public Page(Integer pageSize) {
		super();
		if (pageSize == null || pageSize < 1) {
			this.pageSize = pageSize;
		}
	}


	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber == null || pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE && false) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public int getStart(){
		int start = this.getPageSize()*(this.getPageNumber()-1)+1;
		if(start<0)start = 1;
		return start;
	}
	
	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}


	public QueryObject getQueryObject() {
		return queryObject;
	}


	public void setQueryObject(QueryObject queryObject) {
		this.queryObject = queryObject;
	}


	public String getRandomNumer() {
	    return randomNumer;
	}


	public void setRandomNumer(String randomNumer) {
	    this.randomNumer = Utils.getRandomString();
	}

}
