package com.lwf.mySubscribe.controller;
import com.lwf.mySubscribe.util.JedisUtil;
import com.lwf.mySubscribe.util.SubscribeUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {

    @RequestMapping("/updateAddress")
    public String updateaddress(String address){
        SubscribeUtil.clearMySubscribe();
        return addAddress(address);
    }

    @RequestMapping("/addAddress")
    public String addAddress(String address){
        if(StringUtils.isEmpty(address)){
            return "";
        }
        address = address.trim().replaceAll(" ", "\n").replaceAll(",","\n");
        String[] split = address.split("\n");
        for(String url : split){
            SubscribeUtil.addAddress(url);
        }
        String mySubscribe = SubscribeUtil.updateMySubscribe();
        JedisUtil.updateAddrMap();
        return mySubscribe;
    }

    @RequestMapping("/getMySubscribe")
    public String getDyURL(HttpServletRequest req){

        String remoteAddr = req.getRemoteAddr();
        System.out.println(remoteAddr + " getMySubscribe");

        return SubscribeUtil.getMySubscribe();
    }
}
