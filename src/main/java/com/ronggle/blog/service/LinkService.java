package com.ronggle.blog.service;

import com.jfinal.plugin.activerecord.Page;
import com.ronggle.blog.model.Link;

import java.util.List;

/**
 * Created by soi on 15-11-16.
 */
public interface LinkService {

    /**
     * find link by page
     *
     * @param pageNow  page number
     * @param pageSize page size
     * @return page
     */
    Page<Link> findLink(Integer pageNow, Integer pageSize);

    /**
     * save a link<br/>
     * if link id is null,insert<br/>
     * if link id not null,update
     *
     * @param link link
     * @return boolean
     */
    boolean save(Link link);

    /**
     * find link by id
     * @param linkId link id
     * @return link
     */
    Link findLinkById(String linkId);

    /**
     * find all need show link by cache
     * @return list
     */
    List<Link> findLinkByCache();
}
