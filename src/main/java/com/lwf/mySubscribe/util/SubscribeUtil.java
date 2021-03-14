package com.lwf.mySubscribe.util;

import com.lwf.mySubscribe.bean.Address;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SubscribeUtil {

    private static Map<String, Address> addrMap = new HashMap<>();
    private static String mySubscribe = "";

    public static void addAddress(String address){
        Address addr = new Address(address);
        addrMap.put(addr.getServer(),addr);
    }

    public static String updateMySubscribe(){
        StringBuffer sB = new StringBuffer(1024);
        for(Map.Entry<String, Address> entrySet : addrMap.entrySet()){
            sB.append(entrySet.getValue().getAddr());
            sB.append("\n");
        }
        sB.setLength(sB.length() - 1);
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

    public static String getMySubscribe() {
        return mySubscribe;
    }
}
