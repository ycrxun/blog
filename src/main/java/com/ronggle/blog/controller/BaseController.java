package com.ronggle.blog.controller;

import com.jfinal.core.Controller;
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
}
