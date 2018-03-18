package com.yf.zx.core.base.web;

/**
 * ResultReturn 
 *  结果返回：
 *  1、一般的编辑操作[新增，修改，删除]
 *  2、返回查询结果的查询操作[非分页]
 * @author zhang.yifeng
 * @CreateDate 2017年11月21日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.core.base.web 
 *
 */
public class ResultReturn {

	/** 结果返回码 */
	private String resultCode;
	
	/** 结果描述 */
	private String resultMsg;
	
	/** 返回数据  */
	private Object data;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
