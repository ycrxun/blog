package com.ronggle.blog.controller.client;

import com.jfinal.aop.Duang;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.model.Article;
import com.ronggle.blog.model.Dict;
import com.ronggle.blog.model.Reply;
import com.ronggle.blog.model.Visitor;
import com.ronggle.blog.service.ArticleService;
import com.ronggle.blog.service.VisitorService;
import com.ronggle.blog.service.ReplyService;
import com.ronggle.blog.service.impl.ArticleServiceImpl;
import com.ronggle.blog.service.impl.VisitorServiceImpl;
import com.ronggle.blog.service.impl.ReplyServiceImpl;
import com.ronggle.blog.utils.UuidUtil;

import java.util.List;

/**
 * Created by soi on 15-11-1.
 */
public class ClientController extends BaseController {

    private ArticleService articleService = Duang.duang(ArticleServiceImpl.class);
    private VisitorService visitorService = Duang.duang(VisitorServiceImpl.class);
    private ReplyService replyService = Duang.duang(ReplyServiceImpl.class);

    /**
     * index page
     */
    public void index() {
        //statistical request guest write into database
        Visitor visitor = new Visitor();
        visitor.set("visitor_ip", getRequest().getRemoteAddr());
        visitorService.save(visitor);
        //render to index page
        render("index.html");
    }

    /**
     * article list
     */
    @ActionKey("article")
    public void article() {
        pageNow = getParaToInt(0) > 0 ? getParaToInt(0) : 1;
        pageSize = getParaToInt(1) > 0 ? getParaToInt(1) : pageSize;
        Page<Article> page = CacheKit.get("ArticleCache", "page_" + pageNow);
        if (null == page) {
            page = articleService.findArticle(pageNow, pageSize);
            CacheKit.put("ArticleCache", "page_" + pageNow, page);
        }
        setAttr("page", page).render("article.html");
    }

    /**
     * article detail
     */
    @ActionKey("/article/detail")
    public void detail() {
        String articleId = getPara(0);
        //update hit
        articleService.updateHit(articleId);
        Article article = articleService.findArticleById(articleId);
        setAttr("article", article).render("detail.html");
    }

    /**
     * get hot article list
     */
    @ActionKey("/article/hot")
    public void hot() {
        List<Article> hot = CacheKit.get(Dict.ArticleCache, Dict.HotArticle);
        if (null == hot) {
            hot = articleService.findHotArticle(Dict.HotPageSize).getList();
            CacheKit.put(Dict.ArticleCache, Dict.HotArticle, hot);
        }
        renderJsonForIE(new Record().set("code", 100).set("message", "request success").set("data", hot));
    }

    /**
     * get last publish article
     */
    @ActionKey("/article/last")
    public void last() {
        List<Article> last = CacheKit.get(Dict.ArticleCache, Dict.LastArticle);
        if (null == last) {
            last = articleService.findLastArticle(Dict.LastPageSize).getList();
            CacheKit.put(Dict.ArticleCache, Dict.LastArticle, last);
        }
        renderJsonForIE(new Record().set("code", 100).set("message", "request success").set("data", last));
    }

    /**
     * reply article by id <br/>
     * use ajax submit
     */
    @ActionKey("/article/reply/put")
    public void put() {
        //String articleId = getPara(0);
        Reply reply = getModel(Reply.class);
        //do sth.
        replyService.save(reply);
        //render json
        renderJsonForIE(new Record().set("code", 100).set("message", "request successful...").set("data", reply));
        //redirect("/article/detail/" + reply.getStr("article_id"));
    }

    /**
     * get reply by article id and page number
     */
    @ActionKey("/article/reply")
    public void reply() {
        String articleId = getPara("articleId");
        pageNow = getParaToInt("pageNow");
        if (null == pageNow || pageNow < 0) {
            pageNow = 1;
        }
        pageSize = getParaToInt("pageSize");
        if (null == pageSize || pageSize < 1) {
            pageSize = Dict.PageSize;
        }
        renderJsonForIE(new Record().set("code", 100).set("message", "request successful").set("data", replyService.findReplyByArticleId(articleId, pageNow, pageSize)));
    }

    /**
     * get about page
     */
    @ActionKey("/about")
    public void about() {
        render("about.html");
    }
}
