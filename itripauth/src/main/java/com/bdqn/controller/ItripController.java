package com.bdqn.controller;

import cn.itrip.pojo.ItripHotel;
import cn.itrip.pojo.ItripUser;
import itrip.common.*;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.management.Agent;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ItripController {

    @Resource
    TokenBiz biz;

    @Resource
    RedisUtil RedisUtil;

    @Resource
    ItripHotelMapper dao;

    @Resource
    ItripUserMapper dao1;

    @RequestMapping(value = "/dologin", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Dto login(String name, String password, HttpServletRequest request) throws Exception{
        Map b=new HashMap<>();
        b.put("a",name);
        b.put("b",password);
        ItripUser user=dao.login(name, MD5.getMd5(password,32));
        if (user!=null) {
            String token = biz.generateToken(request.getHeader("User-Agent"),user);
            RedisUtil.setRedis(token,JSONArray.toJSONString(user));
            ItripTokenVO obj=new ItripTokenVO(token, Calendar.getInstance().getTimeInMillis()+7200,Calendar.getInstance().getTimeInMillis());
            return DtoUtil.returnDataSuccess(obj);
        }
return DtoUtil.returnFail("登陆失败","1000");

       /* return JSONArray.toJSONString(user);*/

    }

    @RequestMapping("clist1")
    public String clist(){
        return "clist1";
    }



}