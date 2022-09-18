package service;

import java.util.Date;

public interface Jwtservice {
    //    public String generateToken(String userName, String userId);
//
//    /**
//     * 从令牌中获取用户名
//     *
//     * @param token 令牌
//     * @return 用户名
//     */
//    public String getUsernameFromToken(String token);
//
//    /**
//     * 从令牌中获取用户ID
//     *
//     * @param token 令牌
//     * @return 用户编号
//     */
//    public String getUserIdFromToken(String token);
//
//    /**
//     * 判断令牌是否过期
//     *
//     * @param token 令牌
//     * @return 是否过期
//     */
//    public Boolean isTokenExpired(String token);
//
//    /**
//     * 得到token的过期时间，测试用
//     *
//     * @param token
//     * @return
//     */
//    public Date expirationDate(String token);
//
//    /**
//     * 刷新令牌
//     *
//     * @param token 原令牌
//     * @return 新令牌
//     */
//    public String refreshToken(String token);
//
//    /**
//     * 验证令牌
//     *
//     * @param token    令牌
//     * @param userName 用户
//     * @return 是否有效
//     */
//    public Boolean validateToken(String token, String userName);
//
//    /**
//     * 从claims生成令牌
//     *
//     * @param claims 数据声明
//     * @return 令牌
//     */
    //生成token
    String generateToken(String userName, String userId);

    //验证token
    Boolean isTokenExpired(String token);

}
