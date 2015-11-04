package com.ronggle.blog.controller.admin;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.interceptor.SessionInterceptor;
import com.ronggle.blog.model.Account;
import com.ronggle.blog.model.Dict;
import com.ronggle.blog.service.AccountService;
import com.ronggle.blog.service.impl.AccountServiceImpl;

/**
 * Created by soi on 15-11-1.
 */
@Before(SessionInterceptor.class)
public class AdminIndexController extends BaseController{

    private AccountService accountService = Duang.duang(AccountServiceImpl.class);

    public void index(){
        render("index.html");
    }

    @Clear
    public void login(){
        render("login.html");
    }

    @Clear
    public void token() {
        //do login token
        Account account = accountService.token(getModel(Account.class));
        if (null != account){
            //login success
            setSessionAttr(Dict.ADMIN_SESSION,account.get("password"));
            index();
        }else {
            setAttr("msg","用户名或密码错误");
            login();
        }
    }

    @Clear
    public void logout(){
        //do logout
        removeSessionAttr(Dict.ADMIN_SESSION);
        login();
    }
}
