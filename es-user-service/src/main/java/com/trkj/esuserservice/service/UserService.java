package com.trkj.esuserservice.service;
import com.trkj.jwt.service.Jwtservice;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @DubboReference
    private Jwtservice jwtService;
    public String createJwtToken(String userName,String userId){
        return jwtService.generateToken(userName,userId);
    }
}
