package com.ronggle.blog.service.impl;

import com.jfinal.kit.StrKit;
import com.ronggle.blog.model.Doc;
import com.ronggle.blog.service.DocService;
import com.ronggle.blog.utils.DateUtil;
import com.ronggle.blog.utils.UuidUtil;

/**
 * Created by soi on 15-11-18.
 */
public class DocServiceImpl implements DocService {

    @Override
    public Doc findDocById(String docId) {
        return Doc.dao.findById(docId);
    }

    @Override
    public boolean save(Doc doc) {
        if (StrKit.notBlank(doc.getStr("id"))) {
            doc.set("update_time", DateUtil.getUnixTimestamp());
            return doc.update();
        } else {
            doc.set("id", UuidUtil.getUuid32())
                    .set("create_time", DateUtil.getUnixTimestamp())
                    .set("update_time", DateUtil.getUnixTimestamp());
            return doc.save();
        }

    }
}
