package com.news.admin.pojo;


public class SysRole {
	/**
	 * id 自增
	 */
	private Integer id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色代码，判断角色时根据代码进行判断避免根据角色名称查询时中文乱码
	 */
	private String sym;
	/**
	 * 排序
	 */
	private Integer orderNumber;
	/**
	 * 备注
	 */
	private String descr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSym() {
		return sym;
	}

	public void setSym(String sym) {
		this.sym = sym;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
}
