package com.trkj.jwtservice.service;

import com.trkj.jwtservice.util.JwtTokenUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import com.trkj.jwt.service.Jwtservice;

@DubboService
public class JwtserviceImpl implements Jwtservice {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Boolean validateToken(String token) {
        return jwtTokenUtil.validateToken(token);
    }

    @Override
    public String generateToken(String userName,String userId) {
        return jwtTokenUtil.generateToken(userName,userId);
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return jwtTokenUtil.isTokenExpired(token);
    }
//
//    @Override
//    public String getUsernameFromToken(String token) {
//        return getUsernameFromToken(token);
//    }
//
//    @Override
//    public String getUserIdFromToken(String token) {
//        return getUserIdFromToken(token);
//    }
//
//    @Override
//    public Boolean isTokenExpired(String token) {
//        return isTokenExpired(token);
//    }
//
//    @Override
//    public Date expirationDate(String token) {
//        return expirationDate(token);
//    }
//
//    @Override
//    public String refreshToken(String token) {
//        return refreshToken(token);
//    }
//


}
