package com.ronggle.blog.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.ronggle.blog.model.Doc;

/**
 * Created by soi on 15-11-18.
 */
public interface DocService {

    /**
     * find doc by id
     * @param docId doc id
     * @return doc
     */
    Doc findDocById(String docId);

    /**
     * save one doc<br/>
     * if doc id is null,insert<br/>
     * if doc id not null,update
     * @param doc doc model
     * @return boolean
     */
    boolean save(Doc doc);

    /**
     * find doc by page
     * @param pageNow page number
     * @param pageSize page size
     * @return page
     */
    Page<Doc> findDoc(Integer pageNow, Integer pageSize);

    /**
     * find doc by search
     * @param pageNow page number
     * @param pageSize page size
     * @param search search record
     * @return page
     */
    Page<Doc> findDoc(Integer pageNow, Integer pageSize,Record search);

    /**
     * find doc by search,order
     * @param pageNow page number
     * @param pageSize page size
     * @param search search record(module:?,file_name:?)
     * @param order order record(order_key:?,order:desc/asc),default order is desc
     * @return page
     */
    Page<Doc> findDoc(Integer pageNow, Integer pageSize, Record search, Record order);

    /**
     * delete doc by id
     * @param docId doc id
     * @return boolean
     */
    boolean delete(String docId);
}
