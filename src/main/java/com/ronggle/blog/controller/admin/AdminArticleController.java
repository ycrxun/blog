package com.ronggle.blog.controller.admin;

import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Record;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.interceptor.SessionInterceptor;
import com.ronggle.blog.model.Article;
import com.ronggle.blog.model.Dict;
import com.ronggle.blog.service.ArticleService;
import com.ronggle.blog.service.impl.ArticleServiceImpl;

/**
 * Created by soi on 15-11-4.
 */
//@Before(SessionInterceptor.class)
public class AdminArticleController extends BaseController {

    private ArticleService articleService = Duang.duang(ArticleServiceImpl.class);

    public void index() {
        render("article/article.html");
    }

    /**
     * go to add article page
     */
    public void add() {
        render("article/detail.html");
    }

    /**
     * update one article by id
     */
    public void edit() {
        //do find article
        String articleId = getPara(0);
        setAttr("article", articleService.findArticleById(articleId)).render("article/detail.html");
    }

    /**
     * insert one article to database
     */
    public void save() {
        Article article = getModel(Article.class);
        article.set("content",getPara("md_editor-markdown-doc"));
        article.set("account_id",getRequest().getSession().getAttribute(Dict.ADMIN_SESSION));
        articleService.save(article);
        redirect("/admin/article");
    }

    /**
     * get article stat(hot,not reply)
     */
    public void stat() {
        Record result = new Record()//
                .set("code", 100)//
                .set("message", "request success")//
                .set("data", new Record()//
                        .set("hot", articleService.findHotArticle(Dict.HotPageSize))//
                        .set("not_reply", articleService.findNotReplyArticle(Dict.NotReplyPageSize)));
        renderJsonForIE(result);
    }


    /**
     * find article detail by id
     */
    public void detail(){
        String articleId = getPara(0);
        setAttr("article",articleService.findArticleById(articleId)).render("article/detail.html");
    }

    /**
     * delete article by id
     */
    public void delete(){
        String articleId = getPara(0);
        articleService.delete(articleId);
        redirect("/admin/article");
    }

    /**
     * load article list
     */
    public void list() {
        pageNow = getParaToInt(0);
        //pageSize = getParaToInt(1);
        Record record = new Record()
                .set("code", 100)
                .set("message", "request success")
                .set("data", articleService.findArticle(pageNow, Dict.PageSize));
        renderJsonForIE(record);
    }
}
