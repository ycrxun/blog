package com.ronggle.blog.controller.client;

import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Page;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.model.*;
import com.ronggle.blog.service.ArticleService;
import com.ronggle.blog.service.LinkService;
import com.ronggle.blog.service.VisitorService;
import com.ronggle.blog.service.ReplyService;
import com.ronggle.blog.service.impl.ArticleServiceImpl;
import com.ronggle.blog.service.impl.LinkServiceImpl;
import com.ronggle.blog.service.impl.VisitorServiceImpl;
import com.ronggle.blog.service.impl.ReplyServiceImpl;

import java.util.List;

/**
 * Created by soi on 15-11-1.
 */
public class ClientController extends BaseController {

    private ArticleService articleService = Duang.duang(ArticleServiceImpl.class);
    private VisitorService visitorService = Duang.duang(VisitorServiceImpl.class);
    private ReplyService replyService = Duang.duang(ReplyServiceImpl.class);
    private LinkService linkService = Duang.duang(LinkServiceImpl.class);

    /**
     * index page
     */
    @Clear
    public void index() {
        //statistical request guest write into database
        Visitor visitor = new Visitor();
        visitor.set("visitor_ip", getRequest().getRemoteAddr());
        visitorService.save(visitor);
        //render to index page
        setAttr("title", "Soi个人博客-首页").render("index.html");
    }

    /**
     * article list
     */
    @Clear
    @ActionKey("article")
    public void article() {
        pageNow = getParaToInt(0) > 0 ? getParaToInt(0) : 1;
        pageSize = getParaToInt(1) > 0 ? getParaToInt(1) : pageSize;
        Page<Article> page = articleService.findArticle(pageNow, pageSize);
        setAttr("title", "Soi个人博客-文章列表").setAttr("page", page).render("article.html");
    }

    /**
     * article detail
     */
    @Clear
    @ActionKey("/article/detail")
    public void detail() {
        String articleId = getPara(0);
        //update hit
        articleService.updateHit(articleId);
        Article article = articleService.findArticleById(articleId);
        setAttr("title", "Soi个人博客-文章详情-" + article.get("title")).setAttr("article", article).render("detail.html");
    }

    /**
     * get hot article list
     */
    @Clear
    @ActionKey("/article/hot")
    public void hot() {
        List<Article> hot = articleService.findHotArticle(Dict.HotPageSize).getList();
        success(hot);
    }

    /**
     * get last publish article
     */
    @Clear
    @ActionKey("/article/last")
    public void last() {
        List<Article> last = articleService.findLastArticle(Dict.LastPageSize).getList();
        success(last);
    }

    /**
     * reply article by id <br/>
     * use ajax submit
     */
    @Clear
    @ActionKey("/article/reply/put")
    public void put() {
        //String articleId = getPara(0);
        Reply reply = getModel(Reply.class);
        //do sth.
        replyService.save(reply);
        //update article reply
        articleService.updateReply(reply.getStr("article_id"));
        //render json
        success(reply);
    }

    /**
     * get reply by article id and page number
     */
    @Clear
    @ActionKey("/article/reply")
    public void reply() {
        String articleId = getPara("articleId");
        pageNow = getParaToInt("pageNow");
        if (null == pageNow || pageNow < 0) {
            pageNow = 1;
        }
        pageSize = getParaToInt("pageSize");
        if (null == pageSize || pageSize < 1) {
            pageSize = Dict.PageSize - 5;
        }
        Page<Reply> replyPage = replyService.findReplyByArticleId(articleId, pageNow, pageSize);
        success(replyPage);
    }

    @Clear
    @ActionKey("/link")
    public void link() {
        List<Link> links = linkService.findLinkByCache();
        success(links);
    }

    /**
     * get about page
     */
    @Clear
    @ActionKey("/about")
    public void about() {
        setAttr("title", "Soi个人博客-关于").render("about.html");
    }
}
