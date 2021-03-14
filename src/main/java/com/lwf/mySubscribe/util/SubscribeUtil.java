package com.lwf.mySubscribe.util;

import com.lwf.mySubscribe.bean.Address;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SubscribeUtil {

    private static Map<String, Address> addrMap = null;
    private static String mySubscribe = null;

    public static void addAddress(String address){
        if(addrMap == null){
            JedisUtil.flushAddrMap();
        }
        Address addr = new Address(address);
        addrMap.put(addr.getServer(),addr);
    }

    public static String updateMySubscribe(){
        if(addrMap == null){
            JedisUtil.flushAddrMap();
        }
        StringBuffer sB = new StringBuffer(1024);
        for(Map.Entry<String, Address> entrySet : addrMap.entrySet()){
            sB.append(entrySet.getValue().getAddr());
            sB.append("\n");
        }
        if(sB.length() > 1){
            sB.setLength(sB.length() - 1);
        }
        mySubscribe = BASE64.encode(sB.toString());
        return mySubscribe;
    }

    public static void clearMySubscribe(){
        addrMap.clear();
        mySubscribe = "";
    }

    public static Map<String, Address> getAddrMap() {
        return addrMap;
    }

    public static void setAddrMap(Map<String, Address> map) {
        addrMap = map;
    }

    public static String getMySubscribe() {
        if(StringUtils.isEmpty(mySubscribe)){
            updateMySubscribe();
        }
        return mySubscribe;
    }
}
