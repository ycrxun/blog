package com.ronggle.blog.controller.admin;

import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Record;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.model.Account;
import com.ronggle.blog.model.Dict;
import com.ronggle.blog.model.Status;
import com.ronggle.blog.service.AccountService;
import com.ronggle.blog.service.VisitorService;
import com.ronggle.blog.service.impl.AccountServiceImpl;
import com.ronggle.blog.service.impl.VisitorServiceImpl;
import com.ronggle.blog.utils.DateUtil;

/**
 * Created by soi on 15-11-1.
 * last update 15-11-17
 * update login info store to database
 * update session interceptor to global level
 */
public class AdminIndexController extends BaseController {

    private AccountService accountService = Duang.duang(AccountServiceImpl.class);
    private VisitorService visitorService = Duang.duang(VisitorServiceImpl.class);

    public void index() {
        render("index.html");
    }

    @Clear
    public void login() {
        render("login.html");
    }

    @Clear
    public void token() {
        //do login token
        Account account = accountService.token(getModel(Account.class));
        if (null != account) {
            if (Status.ENABLE.status.equals(account.getInt("status"))) {
                //login success
                setSessionAttr(Dict.ADMIN_SESSION, account.get("id"));
                //update login info
                Integer login_times = account.getInt("login_times");
                account.set("login_times", login_times + 1);
                account.set("last_login_time", DateUtil.getUnixTimestamp());
                account.set("last_login_ip", getRequest().getRemoteAddr());
                accountService.save(account);
                //redirect to index page
                redirect("/admin");
            } else {
                setAttr("msg", "帐号已锁定");
                redirect("/admin/login");
            }
        } else {
            setAttr("msg", "用户名或密码错误");
            redirect("/admin/login");
        }
    }

    /**
     * account logout
     */
    @Clear
    public void logout() {
        //do logout
        removeSessionAttr(Dict.ADMIN_SESSION);
        redirect("/admin/login");
    }

    /**
     * statistical visitor(include today,yesterday,total)
     */
    public void visitor() {
        Record data = new Record()
                .set("today", visitorService.today())
                .set("yesterday", visitorService.yesterday())
                .set("total", visitorService.total());
        success(100,"request successful",data);
    }

}
