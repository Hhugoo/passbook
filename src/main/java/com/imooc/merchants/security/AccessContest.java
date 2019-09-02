package com.imooc.merchants.security;

/**
 * 用ThreadLocal 去单独存储每一个线程携带的Token消息
 */
public class AccessContest {

    private static final ThreadLocal<String> token = new ThreadLocal<String>();

    public static String getToken() {
        return token.get();
    }

    public static void setToken(String tokenStr) {
        token.set(tokenStr);
    }

    public static void clearAccessKey() {
        token.remove();
    }

}
