package com.ronggle.blog.service.impl;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.ronggle.blog.model.Reply;
import com.ronggle.blog.model.Status;
import com.ronggle.blog.service.ReplyService;
import com.ronggle.blog.utils.DateUtil;
import com.ronggle.blog.utils.UuidUtil;

/**
 * Created by soi on 15-11-15.
 */
public class ReplyServiceImpl implements ReplyService {

    @Override
    public Page<Reply> findReplyByArticleId(String articleId, Integer pageNumber, Integer pageSize) {
        return Reply.dao.paginate(pageNumber, pageSize, "select r.*", "from reply as r where 1 = 1 and status = 1 and article_id = ? order by reply_time asc", articleId);
    }

    @Override
    public boolean save(Reply reply) {
        if (StrKit.notBlank(reply.getStr("reply_id"))) {
            //do update
            return reply.update();
        } else {
            reply.set("reply_id", UuidUtil.getUuid32()).set("reply_time", DateUtil.getUnixTimestamp()).set("status", Status.DISABLE.status);
            return reply.save();
        }
    }
}
