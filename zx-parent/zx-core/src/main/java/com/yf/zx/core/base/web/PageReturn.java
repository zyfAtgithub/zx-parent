package com.yf.zx.core.base.web;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class PageReturn<T> {

	/**
	 * 数据状态
	 */
	private int code;
	
	/**
	 * 状态信息
	 */
	private String msg;
	
	/**
	 * 数据总数
	 */
	private long total;
	
	/**
	 * 数据列表
	 */
	private List<T> rows;
	
	public PageReturn(PageInfo<T> pageInfo) {
		this.code = 200;
		this.msg = "查询成功";
		this.total = pageInfo.getTotal();
		this.rows = pageInfo.getList();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageReturn [code=" + code + ", msg=" + msg + ", total=" + total + ", rows=" + rows + "]";
	}
	
}
