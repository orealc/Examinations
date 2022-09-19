package com.trkj.esuserservice.interceptor;


import com.trkj.jwt.service.Jwtservice;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

@Slf4j
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @DubboReference
    private Jwtservice jwtservice;
    @Value("${jwt.header}")
    private String header;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {
        /** 地址过滤 */
        String uri = request.getRequestURI();
        log.debug("请求地址为：" + uri);

        /** Token 验证 */
        String token = request.getHeader(header);
        //  String token = request.getHeader("JWTDemo");
        //findUsesr?id=20&JWTDemo=xcvdvsdsddadsfa
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(header);
        }
        if (StringUtils.isEmpty(token)) {
            throw new SignatureException("token为空");
        }
        if (jwtservice.validateToken(token))
            throw new SignatureException("token失效，请重新登录。");
        /** 设置 identityId 用户身份ID */
        // request.setAttribute("identityId", jwtTokenUtil.getUsernameFromToken(token));
        return true;
    }
}