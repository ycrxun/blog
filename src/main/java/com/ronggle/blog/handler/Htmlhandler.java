package com.ronggle.blog.handler;

import com.jfinal.handler.Handler;
import com.ronggle.blog.model.Dict;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by soi on 15-10-30.
 */
public class Htmlhandler extends Handler {

    @Override
    public void handle(String s, HttpServletRequest request, HttpServletResponse response, boolean[] booleans) {
        s = s.lastIndexOf(Dict.Html) != -1 ? s.substring(0, s.indexOf(Dict.Html)) : s;
        nextHandler.handle(s, request, response, booleans);

    }
}
