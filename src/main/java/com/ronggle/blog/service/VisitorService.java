package com.ronggle.blog.service;

import com.jfinal.plugin.activerecord.Record;
import com.ronggle.blog.model.Visitor;

/**
 * Created by soi on 15-11-6.
 */
public interface VisitorService {

    /**
     * statistical today visitor
     * @return record
     */
    Record today();

    /**
     * statistical yesterday visitor
     * @return record
     */
    Record yesterday();

    /**
     * statistical all visitor
     * @return record
     */
    Record total();

    /**
     * add a visitor to database
     * @param visitor visitor-include ip,time
     */
    void save(Visitor visitor);
}
