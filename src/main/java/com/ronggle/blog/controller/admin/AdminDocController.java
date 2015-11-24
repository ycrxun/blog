package com.ronggle.blog.controller.admin;

import com.jfinal.aop.Duang;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.model.Dict;
import com.ronggle.blog.model.Doc;
import com.ronggle.blog.service.DocService;
import com.ronggle.blog.service.impl.DocServiceImpl;

/**
 * Created by soi on 15-11-24.
 */
public class AdminDocController extends BaseController {

    /**
     * inject service
     */
    private DocService docService = Duang.duang(DocServiceImpl.class);

    /**
     * render to doc index page
     */
    public void index() {
        render("picture/index.html");
    }

    /**
     * ajax find picture list by page number
     */
    public void list() {
        pageNow = getParaToInt("pageNow", 1);
        pageSize = getParaToInt("pageSize", Dict.PageSize);
        String module = getPara("module", "");
        String fileName = getPara("file_name", "");
        Record search = new Record();
        search.set("module", module);
        search.set("file_name", fileName);
        Page<Doc> docPage = docService.findDoc(pageNow, pageSize, search);
        success(docPage);
    }

    public void delete() {
        String docId = getPara(0, "");
        Doc doc = null;
        if (StrKit.notBlank(docId)) {
            doc = docService.findDocById(docId);
        }
        if (null != doc) {

        }

    }

    /**
     * upload index show pic
     */
    public void show() {
    }

    /**
     * upload about banner pic
     */
    public void about() {

    }
}
