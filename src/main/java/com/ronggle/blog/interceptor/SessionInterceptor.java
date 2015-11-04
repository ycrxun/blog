package com.ronggle.blog.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.log.Logger;
import com.ronggle.blog.model.Dict;

/**
 * Created by soi on 15-10-30.
 */
public class SessionInterceptor implements Interceptor{

    private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

    @Override
    public void intercept(Invocation invocation) {
        logger.debug("execute interceptor ---");
        String session = (String) invocation.getController().getSession().getAttribute(Dict.ADMIN_SESSION);
        if (null == session || "".equals(session)){
            invocation.getController().redirect("/admin/login");
        } else {
            invocation.invoke();
        }
    }
}
