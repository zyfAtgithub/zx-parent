package com.yf.zx.core.base.dao;

import java.util.List;

/**
 * BaseDao [Dao操作基类]
 *  
 * @author zhang.yifeng
 * 2017年8月13日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.core.base.dao 
 *
 */
public interface BaseDao<T> {

	public List<T> findList();
}
