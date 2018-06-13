package com.dsb.test;

public class Info {
	
	private int id;
	private String name;
	private int parentId;
	private int orderNo;
	private int levelL;
	private int isLeaf;
	
	public Info(int id, String name, int parentId, int orderNo, int levelL, int isLeaf) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.orderNo = orderNo;
		this.levelL = levelL;
		this.isLeaf = isLeaf;
	}
	
	public Info() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getLevelL() {
		return levelL;
	}
	public void setLevelL(int levelL) {
		this.levelL = levelL;
	}
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	
}
