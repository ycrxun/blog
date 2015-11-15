package com.ronggle.blog.service.impl;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.ronggle.blog.model.Article;
import com.ronggle.blog.service.ArticleService;
import com.ronggle.blog.utils.DateUtil;
import com.ronggle.blog.utils.UuidUtil;

/**
 * Created by soi on 15-11-6.
 */
public class ArticleServiceImpl implements ArticleService {

    @Override
    public Article findArticleById(String articleId) {
        return Article.dao.findFirst("select a.* from article as a where 1 = 1 and a.id = ?", articleId);
    }

    @Override
    public Page<Article> findHotArticle(Integer pageSize) {
        return Article.dao.paginate(1, pageSize, "select a.* ", "from article as a where 1 = 1 and status = 1 order by reply desc, hit desc");
    }

    @Override
    public Page<Article> findNotReplyArticle(Integer pageSize) {
        return Article.dao.paginate(1, pageSize, "select a.*", "from article as a where 1 = 1 and status = 1 and reply = 0 order by create_time desc");
    }

    @Override
    public boolean save(Article article) {
        if (StrKit.notBlank(article.getStr("id"))) {//如果存在Id,更新文章
            article.set("update_time", DateUtil.getUnixTimestamp());
            return article.update();
        } else {//如果不存在Id,添加新文章
            //如果没有填写简介，自动截取文章前100字符
            if (StrKit.isBlank(article.getStr("intro"))) {
                article.set("intro", article.getStr("content").substring(0, 100));
            }
            String articleId = UuidUtil.getUuid32();
            article.set("id", articleId)
                    .set("link", "/article/detail/" + articleId)
                    .set("create_time", DateUtil.getUnixTimestamp())
                    .set("update_time", DateUtil.getUnixTimestamp())
                    .set("status", 1)
                    .set("hit", 0)
                    .set("reply", 0);
            return article.save();
        }
    }

    @Override
    public Page<Article> findArticle(Integer pageNow, Integer pageSize) {
        return Article.dao.paginate(pageNow, pageSize, "select a.*", "from article as a where 1 = 1 order by create_time desc");
    }

    @Override
    public boolean delete(String articleId) {
        return Article.dao.deleteById(articleId);
    }

    @Override
    public boolean updateHit(String articleId) {
        Article article = Article.dao.findById(articleId);
        Integer hit = article.getInt("hit");
        article.set("hit", hit + 1);
        return article.update();
    }

    @Override
    public Page<Article> findLastArticle(Integer lastPageSize) {
        return Article.dao.paginate(1, lastPageSize, "select a.*", "from article as a where 1 = 1 and status = 1 order by create_time desc");
    }
}
