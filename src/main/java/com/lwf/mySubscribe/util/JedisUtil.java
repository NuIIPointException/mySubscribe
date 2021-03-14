package com.lwf.mySubscribe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lwf.mySubscribe.bean.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Component
public class JedisUtil {

    private static ObjectMapper objectMapper;
    private static Jedis jedis;
    private static final String redisAddr = "127.0.0.1";

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        JedisUtil.objectMapper = objectMapper;
    }

    public static void flushAddrMap(){
        if(jedis == null){
            jedis = new Jedis(redisAddr);
        }
        Map<String, Address> addressMap = new HashMap<>();
        String json = jedis.get("addrMap");
        if(StringUtils.isEmpty(json)){
            SubscribeUtil.setAddrMap(addressMap);
            return;
        }
        try {
            Map<String,Map<String,String>> map = objectMapper.readValue(json, Map.class);
            for(Map.Entry<String,Map<String,String>> entry : map.entrySet()){
                Address address = new Address(entry.getValue().get("addr"));
                addressMap.put(address.getServer(),address);
            }
        } catch (JsonProcessingException e) {
            System.err.println("转换异常" + e.getMessage());
        }
        SubscribeUtil.setAddrMap(addressMap);
    }

    public static void updateAddrMap(){
        if(jedis == null){
            jedis = new Jedis(redisAddr);
        }
        String json = "";
        try {
            json = objectMapper.writeValueAsString(SubscribeUtil.getAddrMap());
        } catch (JsonProcessingException e) {
            System.err.println("转换异常" + e.getMessage());
        }
        jedis.set("addrMap",json);
    }
}
