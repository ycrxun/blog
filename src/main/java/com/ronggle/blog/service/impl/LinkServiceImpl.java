package com.ronggle.blog.service.impl;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.ronggle.blog.model.Dict;
import com.ronggle.blog.model.Link;
import com.ronggle.blog.model.Status;
import com.ronggle.blog.service.LinkService;
import com.ronggle.blog.utils.DateUtil;
import com.ronggle.blog.utils.UuidUtil;

import java.util.List;

/**
 * Created by soi on 15-11-16.
 */
public class LinkServiceImpl implements LinkService {

    @Override
    public Page<Link> findLink(Integer pageNow, Integer pageSize) {
        return Link.dao.paginate(pageNow, pageSize, "select l.*", "from link as l where 1 = 1 order by create_time desc");
    }

    @Override
    public boolean save(Link link) {
        //clear cache
        CacheKit.remove(Dict.LinkCache,"link_list");
        if (StrKit.notBlank(link.getStr("id"))) {
            link.set("update_time", DateUtil.getUnixTimestamp());
            return link.update();
        } else {
            link.set("id", UuidUtil.getUuid32())
                    .set("create_time", DateUtil.getUnixTimestamp())
                    .set("update_time", DateUtil.getUnixTimestamp())
                    .set("status", Status.ENABLE.status);
            return link.save();
        }
    }

    @Override
    public Link findLinkById(String linkId) {
        return Link.dao.findById(linkId);
    }

    @Override
    public List<Link> findLinkByCache() {
        return Link.dao.findByCache(Dict.LinkCache, "link_list", "select l.* from link as l where 1 = 1 and l.status = ?", Status.ENABLE.status);
    }


}
