package com.songcw.basecore.grobal;

import com.songcw.basecore.util.AndroidUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sprout on 2018/8/29
 */
public class Grobal {

    public static class Logger {
        public static String Base_Tag = "songcw";           //Log日志的全局TAG
        public static int Method_Count = 0;                 //Log日志打印的方法数
        public static boolean Show_Thread = false;          //Log日志是否打印线程信息
    }

    public static class Http {
//        public static String Url_Dev = "http://192.168.31.113:3000";          //开发环境根地址
        public static String Url_Dev = "http://192.168.2.124:3000";          //开发环境根地址
        public static String Url_Sit = "https://openapi-test.songchedai.com"; //测试环境根地址
        public static String Url_Pro = "https://v200-openapi.songchedai.com"; //生产环境根地址

        public static int ConnectTimeout = 60;            //OkHttp超时配置, 单位: 秒
        public static int ReadTimeout = 60;
        public static int WriteTimeout = 60;

        public static String Code_Succ = "0000";          //响应成功的Code

        public static Map<String, String> buildHeader() {   //公共的Header参数
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("device", AndroidUtil.getDeviceModle());
            return headerMap;
        }
    }
}
