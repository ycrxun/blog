package com.ronggle.blog.service.impl;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.ronggle.blog.model.Article;
import com.ronggle.blog.service.ArticleService;
import com.ronggle.blog.utils.UuidUtil;

/**
 * Created by soi on 15-11-6.
 */
public class ArticleServiceImpl implements ArticleService {

    @Override
    public Article findArticleById(String articleId) {
        return Article.dao.findFirst("select ar.* from article as ar where 1=1 and article_id = ?", articleId);
    }

    @Override
    public Page<Article> findHotArticle(Integer pageSize) {
        return Article.dao.paginate(1, pageSize, "select ar.*", "from article as ar where 1 = 1 and status = 1 and reply > 0 order by reply desc");
    }

    @Override
    public Page<Article> findNotReplyArticle(Integer pageSize) {
        return Article.dao.paginate(1, pageSize, "select ar.*", "from article as ar where 1 = 1 and status = 1 and reply = 0 order by create_time desc");
    }

    @Override
    public boolean save(Article article) {
        if (StrKit.notBlank(article.getStr("article_id"))) {
            return article.update();
        } else {
            return article.set("article_id", UuidUtil.getUuid32()).save();
        }
    }

    @Override
    public Page<Article> findArticle(Integer pageNow, Integer pageSize) {
        return Article.dao.paginate(pageNow, pageSize, "select a.*", "from article as a where 1 = 1 order by create_time desc");
    }
}
