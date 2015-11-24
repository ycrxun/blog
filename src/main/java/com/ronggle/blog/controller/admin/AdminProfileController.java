package com.ronggle.blog.controller.admin;

import com.jfinal.aop.Duang;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.ronggle.blog.controller.BaseController;
import com.ronggle.blog.model.*;
import com.ronggle.blog.service.AccountService;
import com.ronggle.blog.service.DocService;
import com.ronggle.blog.service.impl.AccountServiceImpl;
import com.ronggle.blog.service.impl.DocServiceImpl;
import com.ronggle.blog.utils.FileUtil;
import com.ronggle.blog.utils.ImageUtil;

/**
 * Created by soi on 15-11-17.
 */
public class AdminProfileController extends BaseController {

    private AccountService accountService = Duang.duang(AccountServiceImpl.class);
    private DocService docService = Duang.duang(DocServiceImpl.class);

    public void index() {
        String accountId = (String) getSession().getAttribute(Dict.ADMIN_SESSION);
        Account account = accountService.findAccountById(accountId);
        setAttr("account", account).render("profile/profile.html");
    }

    /**
     * alter account data
     */
    public void alter() {
        Account account = getModel(Account.class);
        if (accountService.save(account)) {
            success(100, "request successful");
        } else {
            error(101, "alter fail");
        }
    }

    /**
     * modify account password
     */
    public void modify() {
        String accountId = (String) getSession().getAttribute(Dict.ADMIN_SESSION);
        String old = getPara("old");
        String last = getPara("last");
        Account account = accountService.findAccountByIdAndPassword(accountId, HashKit.md5(old));
        if (null != account){
            account.set("password",HashKit.md5(last));
            if (accountService.save(account)){
                success(100,"修改成功！");
            } else {
                success(100,"修改失败！");
            }
        }else {
            success(100,"原密码不正确!");
        }
    }

    /**
     * ajax upload avatar
     */
    public void avatar() {
        //get upload file
        Record record = getUpload("avatar", "static/" + FileType.AVATAR.type, 160, 160);
        Account account = getModel(Account.class);
        if (null != record) {
            //save file info to database
            Doc doc = Doc.dao;
            doc.set("file_name", record.get("file_name"));
            doc.set("file_type",record.get("file_type"));
            doc.set("file_size",record.get("file_size"));
            doc.set("real_name", record.get("real_name"));
            doc.set("module",FileType.AVATAR.type);
            doc.set("path",record.get("relative_path"));
            doc.set("md5", FileUtil.getFileMd5(record.getStr("absolute_path")));
            doc.set("status", Status.ENABLE.status);
            docService.save(doc);

            account.set("avatar",record.get("relative_path"));
            accountService.save(account);

            success(100, "upload success", new Record().set("url", record.get("relative_path")));
        } else {
            error(101, "upload image error");
        }
    }

    /**
     * process image
     */
    public void thumbnail() {
        Account account = getModel(Account.class);
        int x = getParaToInt("x");
        int y = getParaToInt("y");
        int w = getParaToInt("w");
        int h = getParaToInt("h");
        String url = getPara("url");
        String root = PathKit.getWebRootPath();
        String file = root + url;
        //System.out.println(file);

        boolean b = ImageUtil.resize(x, y, w, h, file, file);
        if (b) {
            //account.set("avatar",url);
            //accountService.save(account);
            success(100, "thumbnail success");
        } else {
            error(101, "thumbnail fail");
        }
    }
}
