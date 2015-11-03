package com.ronggle.blog.handler;

import com.jfinal.handler.Handler;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by soi on 15-11-2.
 */
public class XssHanlder extends Handler{

    private String exclude;

    public XssHanlder(String exclude) {
        this.exclude = exclude;
    }

    @Override
    public void handle(String s, HttpServletRequest request, HttpServletResponse response, boolean[] booleans) {
        // 对于非静态文件，和非指定排除的url实现过滤
        if (s.indexOf(".") == -1 && !s.startsWith(exclude)){
            request = new XssHttpServletRequestWrapper(request);
        }
        nextHandler.handle(s, request, response, booleans);
    }

    /**
     * Xss过滤Wrapper
     */
    private static class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

        public XssHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        /**
         * 重写并过滤getParameter方法
         */
        @Override
        public String getParameter(String name) {
            return HtmlFilter.getBasicHtmlAndImage(super.getParameter(name));
        }

        /**
         * 重写并过滤getParameterMap方法
         */
        @Override
        public Map<String, String[]> getParameterMap() {
            Map<String, String[]> paraMap = super.getParameterMap();
            // 对于paraMap为空的直接return
            if (null == paraMap || paraMap.isEmpty()) {
                return paraMap;
            }
            for (Map.Entry entry : paraMap.entrySet()) {
                String key = (String) entry.getKey();
                String[] values = (String[]) entry.getValue();
                if (null == values) {
                    continue;
                }
                String[] newValues = new String[values.length];
                for (int i = 0; i < values.length; i++) {
                    newValues[i] = HtmlFilter.getBasicHtmlAndImage(values[i]);
                }
                paraMap.put(key, newValues);
            }
            return paraMap;
        }

        private static class HtmlFilter {
            //在basic基础上  增加图片通过
            public static String getBasicHtmlAndImage(String html) {
                if (html == null)
                    return null;
                return Jsoup.clean(html, Whitelist.basicWithImages());
            }
        }
    }
}



