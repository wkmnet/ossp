/**
 * Create with IntelliJ IDEA
 * Project name : oss
 * Package name : org.oss.config
 * Author : Wukunmeng
 * User : wkm
 * Date : 17-1-17
 * Time : 下午4:14
 * ---------------------------------
 */
package org.oss.config;

import com.jfinal.config.*;
import com.jfinal.core.Controller;
import com.jfinal.handler.Handler;
import com.jfinal.render.ViewType;
import org.oss.controller.HomeController;
import org.oss.interceptor.LoggerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : oss
 * Package name : org.oss.config
 * Author : Wukunmeng
 * User : wkm
 * Date : 17-1-17
 * Time : 下午4:14
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class CommonConfig extends JFinalConfig {
    //日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configConstant(Constants me) {
        me.setEncoding("UTF-8");
        me.setViewType(ViewType.JSP);
        logger.info("开发模式:" + me.getDevMode());
        logger.info("编码:" + me.getEncoding());
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", HomeController.class);
        for(Map.Entry<String, Class<? extends Controller>> entry : me.getEntrySet()){
            logger.info("key=" + entry.getKey() + ",class=" + entry.getValue().getName());
        }
    }

    @Override
    public void configPlugin(Plugins me) {
//        if(prop.getBoolean("open.database")){
//            logger.info("start init database...");
//            initDatabase(me);
//            logger.info("end init database.");
//        }
//        QuartzPlugin quartzPlugin = new QuartzPlugin("jobs.properties");
//        me.add(quartzPlugin);
//
//        me.add(new MailerPlugin());
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new LoggerInterceptor());
//        for(Interceptor i : InterceptorManager.NULL_INTERS){
//            logger.info("Interceptor=" + i.getClass().getName());
//        }
    }

    @Override
    public void configHandler(Handlers me) {
        List<Handler> handlers = me.getHandlerList();
        for(Handler h : handlers){
            logger.info("Handler=" + h.getClass().getName());
        }
    }

    private void initDatabase(Plugins me){
        logger.info(me.toString());
    }
}
