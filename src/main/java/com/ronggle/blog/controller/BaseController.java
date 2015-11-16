package com.ronggle.blog.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;
import com.ronggle.blog.model.Dict;

/**
 * Created by Administrator on 2015/10/30.
 */
public class BaseController extends Controller {

    protected Integer pageNow;
    protected Integer pageSize = Dict.PageSize;

    protected void renderJsonForIE(Object object) {
        render(new JsonRender(object).forIE());
    }

    protected void success() {
        success(100, "request successful");
    }

    protected void success(Object data) {
        success(100, "request successful", data);
    }

    protected void success(Integer code, String message) {
        success(new Record().set("code", code).set("message", message));
    }

    protected void success(Integer code, String message, Object data) {
        success(new Record().set("code", code).set("message", message).set("data", data));
    }

    protected void success(Record record) {
        renderJsonForIE(record);
    }

    protected void error() {
        error(100, "request bad");
    }

    protected void error(Object data) {
        error(100, "request bad", data);
    }

    protected void error(Integer code, String message) {
        error(new Record().set("code", code).set("message", message));
    }

    protected void error(Integer code, String message, Object data) {
        error(new Record().set("code", code).set("message", message).set("data", data));
    }

    protected void error(Record record) {
        renderJsonForIE(record);
    }
}
