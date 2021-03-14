package com.lwf.mySubscribe.bean;

import com.lwf.mySubscribe.util.BASE64;

import java.util.Objects;

public class Address {

    private String addr;
    private String server;
    private String port;
    private String password;
    private String obfs;
    private String method;
    private String protocol;
    private String obfsparam;
    private String remarks;
    private String group;
    private String prefix;

    public Address(String addr) {
        this.addr = addr;
        try{
            String[] split = addr.split("://");
            String decoded = BASE64.decode(split[1]);
            String[] arr = decoded.split(":");
            String[] arr5 = arr[5].split("/\\?");
            String[] last = arr5[1].split("&");
            String obfsparam = last[0].split("=").length > 1 ? last[0].split("=")[1] : "";
            String remarks = last[1].split("=").length > 1 ? last[1].split("=")[1] : "";
            String group = last[2].split("=").length > 1 ? last[2].split("=")[1] : "";

            this.prefix = split[0];
            this.server = arr[0];
            this.port = arr[1];
            this.protocol = arr[2];
            this.method = arr[3];
            this.obfs = arr[4];
            this.password = BASE64.decode(arr5[0]);
            this.obfsparam = obfsparam;
            this.remarks = BASE64.decode(remarks);
            this.group = BASE64.decode(group);
        }catch (Exception e){
            this.server = addr;
            System.err.println(e.getMessage());
            System.err.println("初始化Address失败：" + addr);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address ssr = (Address) o;
        return server.equals(ssr.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, port);
    }

    @Override
    public String toString() {
        return "Address{" + "\n" +
                "addr='" + addr + "'\n" +
                "server='" + server + "'\n" +
                "port='" + port + "'\n" +
                "password='" + password + "'\n" +
                "obfs='" + obfs + "'\n" +
                "method='" + method + "'\n" +
                "protocol='" + protocol + "'\n" +
                "obfsparam='" + obfsparam + "'\n" +
                "remarks='" + remarks + "'\n" +
                "group='" + group + "'\n" +
                '}';
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getObfsparam() {
        return obfsparam;
    }

    public void setObfsparam(String obfsparam) {
        this.obfsparam = obfsparam;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getObfs() {
        return obfs;
    }

    public void setObfs(String obfs) {
        this.obfs = obfs;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
