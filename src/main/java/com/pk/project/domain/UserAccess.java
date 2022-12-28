package com.pk.project.domain;

public class UserAccess {

    //[9/Jun/2015:01:58:09 +0800]
    // private String time;  // 按照指定格式解析出来  yyyyMMdd

    private String ip;
    private String proxyIp;
    private long responseTime;
    private String referer;
    private String method;
    //private String url; // 是否需要存  要经过处理  转存

    private String httpCode;
    private long requestSize;
    private long responseSize;
    private String cache;


    // ========
    private String year;
    private String month;
    private String day;

    private String http;
    private String domain;
    private String path;
    private String params;

    private String province;
    private String city;
    private String isp;

    @Override
    public String toString() {
        return ip + "\t" +
                proxyIp + "\t" +
                responseTime + "\t" +
                referer + "\t" +
                method  + "\t" +
                httpCode  + "\t" +
                requestSize + "\t" +
                responseSize + "\t" +
                cache + "\t" +
                province + "\t" +
                city + "\t" +
                isp + "\t" +
                http + "\t" +
                domain + "\t" +
                path + "\t" +
                params + "\t" +
                year + "\t" +
                month + "\t" +
                day;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public long getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(long requestSize) {
        this.requestSize = requestSize;
    }

    public long getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(long responseSize) {
        this.responseSize = responseSize;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
