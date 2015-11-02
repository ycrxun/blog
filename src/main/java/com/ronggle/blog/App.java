package com.ronggle.blog;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.ronggle.blog.controller.admin.IndexController;
import com.ronggle.blog.controller.client.ClientController;
import com.ronggle.blog.handler.Htmlhandler;
import com.ronggle.blog.model.DbType;
import com.ronggle.blog.model.Dict;
import org.beetl.ext.jfinal.BeetlRenderFactory;

/**
 * Created by Administrator on 2015/10/30.
 */
public class App extends JFinalConfig {

    private static final Logger logger = Logger.getLogger(App.class);

    private Prop prop = null;

    @Override
    public void configConstant(Constants constants) {
        //load config file
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
        routes.add("/admin", IndexController.class,Dict.Admin);
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

        plugins.add(local_arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {
        handlers.add(new Htmlhandler());
    }
}
