package com.ronggle.blog.controller.admin;

import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Page;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.interceptor.SessionInterceptor;
import com.ronggle.blog.model.Dict;
import com.ronggle.blog.model.Link;
import com.ronggle.blog.service.LinkService;
import com.ronggle.blog.service.impl.LinkServiceImpl;

/**
 * Created by soi on 15-11-16.
 */
//@Before(SessionInterceptor.class)
public class AdminLinkController extends BaseController{

    private LinkService linkService = Duang.duang(LinkServiceImpl.class);

    /**
     * render to link list page
     */
    public void index(){
        render("link/link.html");
    }

    /**
     * ajax get link list
     */
    public void list(){
        pageNow = getParaToInt("pageNow");
        if (null == pageNow || pageNow < 0) {
            pageNow = 1;
        }
        Page<Link> linkPage = linkService.findLink(pageNow, Dict.PageSize);
        success(linkPage);
    }

    /**
     * render add html
     */
    public void add(){
        render("link/form.html");
    }

    /**
     * render update html,before,load link by id
     */
    public void update(){
        String linkId = getPara(0);
        setAttr("link",linkService.findLinkById(linkId)).render("link/form.html");
    }

    /**
     * save a link
     */
    public void put(){
        Link link = getModel(Link.class);
        if (null == link){
            error();
        }else {
            linkService.save(link);
            success();
        }
    }

    /**
     * delete a link by id
     */
    public void delete(){
        String linkId = getPara(0);
        if (linkService.delete(linkId)) {
            success();
        } else {
            error();
        }
    }
}
