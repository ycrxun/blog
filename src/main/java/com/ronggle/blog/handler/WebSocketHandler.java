package com.ronggle.blog.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by soi on 15-11-18.
 */
public class WebSocketHandler extends Handler {

    @Override
    public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean[] booleans) {

        /**
         * WebSocket不交给JFinal处理
         */
        if (!s.contains("/socket")) {
            nextHandler.handle(s,httpServletRequest,httpServletResponse,booleans);
        }
    }
}
