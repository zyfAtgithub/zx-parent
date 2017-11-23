package com.yf.zx.core.util.conf;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.yf.zx.core.util.common.StringUtils;

/**
 * SysPropertyConf 系统配置加载
 *  使用方式：在需要使用的类中使用@Autowired注解注入即可。
 * @author zhang.yifeng
 * @CreateDate 2017年10月27日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.core.util.conf 
 *
 */
public class SysPropertyConf extends PropertyPlaceholderConfigurer  {
	 
		// 存取properties配置文件key-value结果
	   private Properties props;      

	    @Override
	    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
	                            throws BeansException {
	        super.processProperties(beanFactoryToProcess, props);
	        this.props = props;
	    }

	    public String getProperty(String key){
	        return this.props.getProperty(key);
	    }

	    public Integer getIntValue(String key){
	    	String val = getProperty(key);
	    	if (StringUtils.isNullOrEmpty(val)) {
	    		return null;
	    	}
	    	return Integer.parseInt(val);
	    }

	    public Long getLongValue(String key){
	    	String val = getProperty(key);
	    	if (StringUtils.isNullOrEmpty(val)) {
	    		return null;
	    	}
	    	return Long.parseLong(val);
	    }

	    public String getProperty(String key, String defaultValue) {
	        return this.props.getProperty(key, defaultValue);
	    }

	    public Object setProperty(String key, String value) {
	        return this.props.setProperty(key, value);
	    }
}
