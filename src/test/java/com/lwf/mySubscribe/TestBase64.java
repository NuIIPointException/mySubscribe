package com.lwf.mySubscribe;

import com.lwf.mySubscribe.util.BASE64;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

public class TestBase64 {
    public static void main(String[] args) {
        String s = "NDUuMTMwLjE0Ni4xOTI6ODgxMjpvcmlnaW46cmM0OnBsYWluOmJHNWpiaTV2Y21jZ05qSS8_b2Jmc3BhcmFtPSZyZW1hcmtzPTVMLUU1NzJYNXBhdlRBJmdyb3VwPVRHNWpiaTV2Y21j";
/*        String s1 = Base64Utils.encodeToString(s.getBytes(StandardCharsets.UTF_8));
        System.out.println(s1);
        byte[] bytes = Base64Utils.decodeFromString(s);
        System.out.println(new String(bytes));*/

        String decoded = BASE64.decode(s);
        String[] arr = decoded.split(":");
        String[] arr5 = arr[5].split("/\\?");
        String other = arr5[1];
        String[] otherArr = other.split("&");
        String password = arr5[0];
        String obfsparam = otherArr[0].substring(otherArr[0].indexOf("=") + 1);
        String remarks = otherArr[1].substring(otherArr[1].indexOf("=") + 1);
        String group = otherArr[2].substring(otherArr[2].indexOf("=") + 1);

    }
}
