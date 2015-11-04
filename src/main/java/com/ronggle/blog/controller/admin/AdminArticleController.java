package com.ronggle.blog.controller.admin;

import com.jfinal.aop.Before;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.interceptor.SessionInterceptor;

/**
 * Created by soi on 15-11-4.
 */
@Before(SessionInterceptor.class)
public class AdminArticleController extends BaseController{


    public void index(){
        render("article.html");
    }

    public void add() {
        render("detail.html");
    }

    public void edit(){
        //do find article
        render("detail.html");
    }

    public void save(){
        //no id add
        //have id update
        index();
    }
}
