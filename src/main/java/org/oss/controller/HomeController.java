/**
 * Create with IntelliJ IDEA
 * Project name : oss
 * Package name : org.oss.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 17-1-17
 * Time : 下午4:31
 * ---------------------------------
 */
package org.oss.controller;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import javax.servlet.http.Cookie;

/**
 * Create with IntelliJ IDEA
 * Project name : oss
 * Package name : org.oss.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 17-1-17
 * Time : 下午4:31
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class HomeController extends BaseController {


    public void login(){
        String name = getPara("name");
        String password = getPara("password");
        logger.info("user:{};password:{}",name,password);
        logger.info("host:{}",host());
        Response response = null;
        try {
            Request request = Request.Post(userCenterUrl + "/user/login");
            request.bodyForm(Form.form().add("user_name",name).add("user_password",password).build());
            response = request.execute();
            JSONObject body = JSONObject.fromObject(response.returnContent().asString());
            logger.info("body:{}",body.toString(4));
            if(body.getBoolean("success")){
                JSONObject data = body.getJSONObject("data");
                Cookie cookie = new Cookie("user_cookie_key",data.getString("token"));
                cookie.setDomain(host());
                cookie.setMaxAge(60*5);
                setCookie(cookie);
                render("/html/success.html");
                return;
            }
        } catch (Exception e){
            logger.error("message:{}",e);
            render("/html/fail.html");
        } finally {
            if(response != null){
                response.discardContent();

            }
        }
        render("/html/fail.html");
    }

    public void check(){
        String value = getCookieValue("user_cookie_key");
        logger.info("user:{}",value);
        if(StringUtils.isBlank(value)){
            render("/html/fail.html");
        } else {
            render("/html/success.html");
        }
    }

}
