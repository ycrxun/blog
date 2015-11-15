package com.ronggle.blog.service;

import com.jfinal.plugin.activerecord.Page;
import com.ronggle.blog.model.Article;

import java.util.List;

/**
 * Created by soi on 15-11-6.
 */
public interface ArticleService {

    /**
     * find one article by article id
     * @param articleId article id
     * @return article
     */
    Article findArticleById(String articleId);

    /**
     * find hot article page by page size
     * @param pageSize page size
     * @return page
     */
    Page<Article> findHotArticle(Integer pageSize);

    /**
     * find not reply article page by page size
     * @param pageSize page size
     * @return page
     */
    Page<Article> findNotReplyArticle(Integer pageSize);

    /**
     * save an article-if id is null execute insert, if id not null execute update
     * @param article article model
     */
    boolean save(Article article);

    /**
     * find article by page
     * @param pageNow current page number
     * @param pageSize total record sum
     * @return page
     */
    Page<Article> findArticle(Integer pageNow, Integer pageSize);

    /**
     * delete article by id
     * @param articleId article id
     * @return boolean
     */
    boolean delete(String articleId);

    /**
     * update article hit
     * @param articleId article id
     * @return boolean
     */
    boolean updateHit(String articleId);

    /**
     * find last publish article
     * @param lastPageSize page size
     * @return page
     */
    Page<Article> findLastArticle(Integer lastPageSize);
}
