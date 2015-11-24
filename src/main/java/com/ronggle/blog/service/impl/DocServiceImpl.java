package com.ronggle.blog.service.impl;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
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

    @Override
    public Page<Doc> findDoc(Integer pageNow, Integer pageSize) {
        return findDoc(pageNow, pageSize, null);
    }

    @Override
    public Page<Doc> findDoc(Integer pageNow, Integer pageSize, Record search) {
        return findDoc(pageNow, pageSize, search, new Record().set("order_by", "create_time").set("order", "desc"));
    }

    @Override
    public Page<Doc> findDoc(Integer pageNow, Integer pageSize, Record search, Record order) {
        StringBuilder sql = new StringBuilder("from doc as d where 1 = 1");
        if (null != search) {
            String module = search.get("module");
            if (StrKit.notBlank(module)) {
                sql.append(" and d.module = ");
                sql.append(module);
            }
            sql.append(" and d.file_name like ");
            sql.append("'%").append(search.get("file_name")).append("%'");
        }
        if (null != order) {
            sql.append(" order by ");
            sql.append(order.get("order_by"));
            sql.append(" ");
            sql.append(order.get("order"));
        }
        return Doc.dao.paginate(pageNow, pageSize, "select d.*", sql.toString());
    }

    @Override
    public boolean delete(String docId) {
        return Doc.dao.deleteById(docId);
    }

}
