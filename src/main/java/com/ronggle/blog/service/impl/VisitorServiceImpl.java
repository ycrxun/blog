package com.ronggle.blog.service.impl;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ronggle.blog.model.Visitor;
import com.ronggle.blog.service.VisitorService;
import com.ronggle.blog.utils.DateUtil;
import com.ronggle.blog.utils.UuidUtil;

/**
 * Created by soi on 15-11-6.
 */
public class VisitorServiceImpl implements VisitorService {

    @Override
    public Record today() {
        return Db.findFirst("select count(*) as visitor from visitor where 1=1 and date_format(from_unixtime(visitor_time),'%Y-%m-%d') =  date_format(now(),'%Y-%m-%d')");
    }

    @Override
    public Record yesterday() {
        return Db.findFirst("select count(*) as visitor from visitor where 1 = 1 and date_format(now(),'%Y%m%d') - date_format(from_unixtime(visitor_time),'%Y%m%d') = 1");
    }

    @Override
    public Record total() {
        return Db.findFirst("select count(*) as visitor from visitor where 1 = 1");
    }

    @Override
    public void save(Visitor visitor) {
        visitor.set("visitor_id", UuidUtil.getUuid32()).set("visitor_time", DateUtil.getUnixTimestamp()).save();
    }
}
