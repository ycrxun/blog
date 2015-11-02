package com.ronggle.blog.controller.client;

import com.jfinal.core.ActionKey;
import com.ronggle.blog.controller.BaseController;

/**
 * Created by soi on 15-11-1.
 */
public class ClientController extends BaseController {

    public void index(){
        render("index.html");
    }

    @ActionKey("article")
    public void article(){
        pageNow = getParaToInt(0) > 0 ? getParaToInt(0) : 1;
        pageSize = getParaToInt(1) > 0 ? getParaToInt(1) : pageSize;
        render("article.html");
    }

    @ActionKey("/article/detail")
    public void detail(){
        Integer articleId = getParaToInt(0);
        render("detail.html");
    }
}
