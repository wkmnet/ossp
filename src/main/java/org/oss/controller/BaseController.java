/**
 * Create with IntelliJ IDEA
 * Project name : oss
 * Package name : org.oss.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 17-1-17
 * Time : 下午4:30
 * ---------------------------------
 */
package org.oss.controller;

import com.jfinal.core.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;

/**
 * Create with IntelliJ IDEA
 * Project name : oss
 * Package name : org.oss.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 17-1-17
 * Time : 下午4:30
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class BaseController extends Controller {

    //日志
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String userCenterUrl = "http://user-center.wkm.vmovier.cc/api";

    public void index(){
        logger.info("request home page:index.jsp");
        render("/html/index.html");
        logger.info("request home page end:index.jsp");
    }

    protected String getCookieValue(String name){
        Cookie[] cookies = getRequest().getCookies();
        if(cookies == null || cookies.length == 0){
            return null;
        }
        for(Cookie c:cookies){
            logger.info("cookie:{}",c.toString());
            if(host().equals(c.getDomain())){
                if(name.equals(c.getName())){
                    return c.getValue();
                }
            }
        }
        return null;
    }

    protected String host(){
        String host = getRequest().getHeader("Host");
        if(host.indexOf(':') > 0){
            return host.substring(0,host.indexOf(':'));
        }
        return host;
    }

}
