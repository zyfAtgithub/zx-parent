package com.yf.zx.core.base.vo;

public class BaseVo {

	private int pageNumber;
	
	private int pageSize;
	
	/**
	 * 排序
	 * 样例：
	 * id asc, lastloginTime dec
	 */
	private String orderBy;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "BaseVo [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", orderBy=" + orderBy + "]";
	}
}
