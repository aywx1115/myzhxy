package com.atguigu.myzhxy.util;

import javax.servlet.http.HttpServletRequest;

public class AuthContextHolder {

    public static Long getUserIdToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        Long userId = JwtHelper.getUserId(token);
        return userId;
    }

    public static String getUserName(HttpServletRequest request) {

        String token = request.getHeader("token");
        String userName = JwtHelper.getUserName(token);
        return userName;
    }
}
