package com.ronggle.blog;

import com.jfinal.config.*;
import com.jfinal.log.Logger;
import com.ronggle.blog.controller.IndexController;
import org.beetl.ext.jfinal.BeetlRenderFactory;

/**
 * Created by Administrator on 2015/10/30.
 */
public class App extends JFinalConfig{

    private static final Logger logger = Logger.getLogger(App.class);

    @Override
    public void configConstant(Constants constants) {
        //
        constants.setMainRenderFactory(new BeetlRenderFactory());
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", IndexController.class);
    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
