package com.ronggle.blog;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.ronggle.blog.controller.admin.*;
import com.ronggle.blog.controller.client.ClientController;
import com.ronggle.blog.handler.Htmlhandler;
import com.ronggle.blog.handler.XssHandler;
import com.ronggle.blog.interceptor.SessionInterceptor;
import com.ronggle.blog.model.*;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/10/30.
 */
public class App extends JFinalConfig {

    private static final Logger logger = Logger.getLogger(App.class);

    private Prop prop = null;

    @Override
    public void configConstant(Constants constants) {
        //load config file
        logger.info("load config file in class path...");
        prop = PropKit.use("global.properties");
        constants.setDevMode(prop.getBoolean("app.dev.mode", false));
        constants.setMainRenderFactory(new BeetlRenderFactory());
    }

    @Override
    public void configRoute(Routes routes) {
        clientRoutes(routes);
        adminRoutes(routes);
    }

    private void adminRoutes(Routes routes) {
        routes.add("/admin", AdminIndexController.class, Dict.Admin);
        routes.add("/admin/article", AdminArticleController.class, Dict.Admin);
        routes.add("/admin/link", AdminLinkController.class, Dict.Admin);
        routes.add("/admin/profile", AdminProfileController.class, Dict.Admin);
    }

    private void clientRoutes(Routes routes) {
        routes.add("/", ClientController.class, Dict.Front);
    }

    @Override
    public void configPlugin(Plugins plugins) {
        //config druid data source plugin
        DruidPlugin local_dp = new DruidPlugin(
                prop.get("jdbc.url"),
                prop.get("jdbc.username"),
                prop.get("jdbc.password")
        );

        WallFilter wall = new WallFilter();
        wall.setDbType(DbType.MySQL.TYPE);
        local_dp.addFilter(wall);

        StatFilter stat = new StatFilter();
        stat.setDbType(DbType.MySQL.TYPE);
        local_dp.addFilter(stat);

        plugins.add(local_dp);

        ActiveRecordPlugin local_arp = new ActiveRecordPlugin(Dict.Local, local_dp);
        local_arp.setDevMode(prop.getBoolean("app.dev.mode", false));
        local_arp.setShowSql(local_arp.getDevMode());

        local_arp.addMapping("account", Account.class);
        local_arp.addMapping("article", Article.class);
        local_arp.addMapping("reply", Reply.class);
        local_arp.addMapping("link", Link.class);
        local_arp.addMapping("menu", Menu.class);
        local_arp.addMapping("visitor", Visitor.class);
        local_arp.addMapping("doc", Doc.class);

        plugins.add(local_arp);

        //config ehcache plugin
        plugins.add(new EhCachePlugin());
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new SessionInterceptor());
        interceptors.add(new TxByMethods("save", "update", "delete"));
        interceptors.add(new TxByRegex(".*save.*"));
        interceptors.add(new TxByRegex(".*update.*"));
        interceptors.add(new TxByRegex(".*delete.*"));
    }

    @Override
    public void configHandler(Handlers handlers) {
        //global context
        handlers.add(new ContextPathHandler("ctx"));
        //处理.html后缀的请求
        handlers.add(new Htmlhandler());
        //排除后台管理请求的Xss过滤
        handlers.add(new XssHandler("/admin"));
        //add druid handler
        handlers.add(new DruidStatViewHandler("/admin/data", new IDruidStatViewAuth() {

            @Override
            public boolean isPermitted(HttpServletRequest request) {
                //if admin session not null,show druid stat view
                return null != request.getSession().getAttribute(Dict.ADMIN_SESSION);
            }
        }));
    }

}
