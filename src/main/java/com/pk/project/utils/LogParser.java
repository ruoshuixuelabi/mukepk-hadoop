package com.pk.project.utils;

import com.pk.project.domain.UserAccess;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日志解析类
 */
public class LogParser {


    // day=2023328  20231002
    public static void main(String[] args)  throws Exception {
        String log = "[28/10/2023:10:11:30 +0800]\t139.212.242.189\t-\t126\t-\tGET\thttps://coding.imooc.com/class/357.html\t404\t67\t-\tHIT\t\n";

        String[] splits = log.split("\t");
        String time = splits[0].trim();
        String ip = splits[1].trim();
        String proxyIp = splits[2].trim();
        String responseTime = splits[3].trim();
        String referer = splits[4].trim();
        String method = splits[5].trim();
        String url = splits[6].trim();
        String httpCode = splits[7].trim();
        String requestSize = splits[8].trim();
        String responseSize = splits[9].trim();
        String cache = splits[10].trim();

        UserAccess access = new UserAccess();
        access.setIp(ip);
        access.setProxyIp(proxyIp);
        access.setMethod(method);
        access.setReferer(referer);
        access.setHttpCode(httpCode);
        access.setCache(cache);
        access.setResponseTime(Long.parseLong(responseTime));
        access.setRequestSize(Long.parseLong(requestSize));
        access.setResponseSize(Long.parseLong(responseSize));

        SimpleDateFormat format = new SimpleDateFormat("[dd/MM/yyyy:HH:mm:ss +0800]");
        Date date = format.parse(time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        access.setYear(year + "");
        access.setMonth(month < 10 ? "0" + month : month + "");
        access.setDay(day < 10 ? "0" + day : day + "");

        URL u = new URL(url);
        access.setDomain(u.getHost());
        access.setHttp(u.getProtocol());
        access.setPath(u.getPath());
        access.setParams(u.getQuery());

        // TODO...
        access.setProvince("-");
        access.setCity("-");
        access.setIsp("-");

        System.out.println(access);

    }
}
