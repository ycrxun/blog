package com.ronggle.blog.service;

import com.jfinal.plugin.activerecord.Page;
import com.ronggle.blog.model.Reply;

/**
 * Created by soi on 15-11-15.
 */
public interface ReplyService {

    /**
     * find reply by article id,page number and page size
     *
     * @param articleId article id
     * @return page
     */
    Page<Reply> findReplyByArticleId(String articleId, Integer pageNumber, Integer pageSize);

    /**
     * save a reply<br/>
     * if have reply id, update<br/>
     * if not reply id,insert reply
     *
     * @param reply reply model
     * @return boolean
     */
    boolean save(Reply reply);
}
