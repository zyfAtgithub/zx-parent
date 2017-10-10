package com.yf.zx.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yf.zx.user.dao.UserMapper;
import com.yf.zx.user.model.User;
import com.yf.zx.user.model.UserExample;

@Service("userService")
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserMapper userDao;
	public boolean existUser(String name) {
        UserExample userE = new UserExample();
        userE.createCriteria().andNameEqualTo(name);
        userE.setDistinct(true);
        List<User> user = userDao.selectByExample(userE);
        logger.info(user.toString());
        if (user.size() > 0)
            return true;
        else
            return false;
    }
	
	/**
	 *  REQUIRED:业务方法需要在一个容器里运行。如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务。
        
        NOT_SUPPORTED:声明方法不需要事务。如果方法没有关联到一个事务，容器不会为他开启事务，如果方法在一个事务中被调用，
        				该事务会被挂起，调用结束后，原先的事务会恢复执行。
        				
        REQUIRESNEW:不管是否存在事务，该方法总汇为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务挂起，新的事务被创建。
        
        MANDATORY：该方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果在没有事务的环境下被调用，容器抛出例外。
        
        SUPPORTS:该方法在某个事务范围内被调用，则方法成为该事务的一部分。如果方法在该事务范围外被调用，该方法就在没有事务的环境下执行。
        
        NEVER：该方法绝对不能在事务范围内执行。如果在就抛例外。只有该方法没有关联到任何事务，才正常执行。
        
        NESTED:如果一个活动的事务存在，则运行在一个嵌套的事务中。如果没有活动事务，则按REQUIRED属性执行。
        	它使用了一个单独的事务，这个事务拥有多个可以回滚的保存点。内部事务的回滚不会对外部事务造成影响。
    		它只对DataSourceTransactionManager事务管理器起效。
	 *  
	 * @author zhang.yifeng 
	 * @param user
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void addUser(User user) {
		userDao.insert(user);
	}
}
