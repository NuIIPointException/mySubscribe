package com.lwf.mySubscribe.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64 {
    private static final BASE64Encoder encoder = new BASE64Encoder();
    private static final BASE64Decoder decoder = new BASE64Decoder();
    private static final String DEFAULT_CHARSET = "UTF-8";

    //编码
    public static String encode(String text){
        return encode(text, DEFAULT_CHARSET);
    }

    //解码
    public static String decode(String encodedText){
        return decode(encodedText, DEFAULT_CHARSET);
    }

    //编码
    public static String encode(String text, String charSet){
        String decodedText = "";
        try {
            decodedText = encoder.encode(text.getBytes(charSet));
        }catch (Exception e){
            e.printStackTrace();
        }
        return decodedText;
    }

    //解码
    public static String decode(String encodedText, String charSet){
        String decodedText = "";
        if(isBlank(encodedText)){
            return decodedText;
        }
        encodedText = encodedText.trim();
        while(encodedText.length() % 4 != 0){
            encodedText += "=";
        }
        try {
            decodedText = new String(decoder.decodeBuffer(encodedText), charSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return decodedText;
    }

    public static boolean isBlank(String str){
        return str == null || "".equals(str) || "".equals(str.trim());
    }
}
