package com.ronggle.blog.controller.client;

import com.jfinal.aop.Duang;
import com.jfinal.core.ActionKey;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.model.Article;
import com.ronggle.blog.model.Visitor;
import com.ronggle.blog.service.ArticleService;
import com.ronggle.blog.service.VisitorService;
import com.ronggle.blog.service.impl.ArticleServiceImpl;
import com.ronggle.blog.service.impl.VisitorServiceImpl;
import com.ronggle.blog.utils.UuidUtil;

/**
 * Created by soi on 15-11-1.
 */
public class ClientController extends BaseController {

    private ArticleService articleService = Duang.duang(ArticleServiceImpl.class);
    private VisitorService visitorService = Duang.duang(VisitorServiceImpl.class);
    /**
     * index page
     */
    public void index(){
        //statistical request guest write into database
        Visitor visitor = new Visitor();
        visitor.set("visitor_ip",getRequest().getRemoteAddr());
        visitorService.save(visitor);
        //render to index page
        render("index.html");
    }

    /**
     * article list
     */
    @ActionKey("article")
    public void article(){
        pageNow = getParaToInt(0) > 0 ? getParaToInt(0) : 1;
        pageSize = getParaToInt(1) > 0 ? getParaToInt(1) : pageSize;
        setAttr("page",articleService.findArticle(pageNow,pageSize)).render("article.html");
    }

    /**
     * article detail
     */
    @ActionKey("/article/detail")
    public void detail(){
        String articleId = getPara(0);
        //update hit
        articleService.updateHit(articleId);
        Article article = articleService.findArticleById(articleId);
        setAttr("article",article).render("detail.html");
    }
}
