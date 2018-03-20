package com.yf.zx.web.shiro.filter;

import com.yf.zx.biz.log.login.entity.LoginLog;
import com.yf.zx.biz.log.login.service.LoginLogService;
import com.yf.zx.core.util.constants.Constants;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Date;

/**
 * 退出过滤器
 */
public class MyLogoutFilter extends LogoutFilter {

    Logger logger = LoggerFactory.getLogger(MyLogoutFilter.class);

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 覆写父类的退出处理方法，添加日志记录逻辑
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        try {
            Subject subject = getSubject(request, response);
            Session session = subject.getSession();
            Long loginLogId = (Long) session.getAttribute(Constants.CUR_LOGIN_LOGID);
            if (loginLogId != null) {
                LoginLog loginLog = loginLogService.findById(loginLogId);
                if (loginLog != null) {
                    loginLog.setLogouttime(new Date());
                    loginLogService.editById(loginLog);
                }
            }
        } catch (Exception e) {
            logger.error("记录登录日志出错！！", e);
        }
        return super.preHandle(request, response);
    }
}
