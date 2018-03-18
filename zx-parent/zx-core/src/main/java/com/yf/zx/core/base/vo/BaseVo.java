package com.yf.zx.core.base.vo;

public class BaseVo {

	private int page;
	
	private int rows;
	
	/**
	 * 排序
	 * 样例：
	 * id asc, lastloginTime dec
	 */
	private String orderBy;


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "BaseVo [page=" + page + ", rows=" + rows + ", orderBy=" + orderBy + "]";
	}
}
