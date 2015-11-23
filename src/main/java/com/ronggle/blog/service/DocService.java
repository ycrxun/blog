package com.ronggle.blog.service;

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
}
