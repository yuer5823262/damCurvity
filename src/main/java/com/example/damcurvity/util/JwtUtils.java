package com.example.damcurvity.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.damcurvity.entity.BaseUser;
import com.example.damcurvity.exception.DamPourException;
import com.example.damcurvity.exception.DampouringExceptionEnum;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * @author admin
 */
public class JwtUtils {

    /**
     * 获取token
     * @param u user
     * @return token
     */
    private static final String Sign = "~(K!@$$#&*dg|{nx2e";
    public static String getToken(BaseUser u) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId", u.getId())
                .withClaim("username", u.getUsername())
                .withClaim("pwd", u.getPwd())
                .withClaim("role", u.getRole());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(Sign));
    }

    /**
     * 验证token合法性 成功返回token
     */


    public static DecodedJWT verify(String token){
        if(StringUtils.isEmpty(token)){
            throw new DamPourException(DampouringExceptionEnum.NEED_LOGIN);
        }
        JWTVerifier build = JWT.require(Algorithm.HMAC256(Sign)).build();
        return build.verify(token);
    }

    public static String GetUserName(String jwt) {
        try {
            String username = verify(jwt).getClaims().get("username").asString();
            return username;
        } catch (Exception e) {
            return null;
        }
    }
    public static Integer GetRole(String jwt) {
        try {
            Integer role = verify(jwt).getClaims().get("role").asInt();
            return role;
        } catch (Exception e) {
            return null;
        }
    }
    public static Integer GetId(String jwt) {
        try {
            Integer id = verify(jwt).getClaims().get("userId").asInt();
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    public static BaseUser GetUser(String jwt) {
        try {
            Map<String, Claim> Claims = verify(jwt).getClaims();
            BaseUser user = new BaseUser();
            user.setUsername(Claims.get("username").asString());
            user.setPwd(Claims.get("pwd").asString());
            user.setId(Claims.get("id").asInt());
            user.setRole(Claims.get("role").asInt());
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}
