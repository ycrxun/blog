package com.ronggle.blog.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by soi on 15-10-30.
 */
public class MonitorInterceptor implements Interceptor{

    @Override
    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
