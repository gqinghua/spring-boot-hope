package com.data.hope.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.data.hope.ReportProperties;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * JWT工具类
 * @author guoqinghua
 * @since 2021-01-09
 */
public class JwtBean {

    private ReportProperties reportProperties;

    public  JwtBean() {
    }

    public JwtBean(ReportProperties reportProperties) {
        this.reportProperties = reportProperties;
    }

    public ReportProperties getReportProperties() {
        return reportProperties;
    }

    /**
     * 旧项目没有type 和 tenantCode
     * update by lide
     * @param username 用户名
     * @param uuid
     * @return
     */
    public String createToken(String username, String uuid) {
        String token = JWT.create()
//                .withExpiresAt(DateUtils.toDate(LocalDateTime.now().plusHours(4)))
                .withClaim("username", username)
                .withClaim("uuid", uuid)
                .sign(Algorithm.HMAC256(reportProperties.getSecurity().getJwtSecret()));
        return token;
    }
    /**
     * 秘钥8
     * @param username 用户名
     * @return
     */
    public String createToken(String username, String uuid,Integer type,String tenantCode) {
        String token = JWT.create()
//                .withExpiresAt(DateUtils.toDate(LocalDateTime.now().plusHours(4)))
                .withClaim("username", username)
                .withClaim("uuid", uuid)
                .withClaim("type", type)
                .withClaim("tenant", tenantCode)
                .sign(Algorithm.HMAC256(reportProperties.getSecurity().getJwtSecret()));
        return token;
    }

    /**
     * 根据用户名、角色、权限、菜单等信息生成token
     * @param username 用户名
     * @param roles 角色
     * @param authorities 权限
     * @param menus 菜单
     * @return
     */
    public String createToken(String username, List<String> roles, List<String> authorities, List<String> menus) {

        JWTCreator.Builder builder = JWT.create();
        //角色
        if (!CollectionUtils.isEmpty(roles)) {
            builder.withClaim("role", roles);
        }

        //权限
        if (!CollectionUtils.isEmpty(authorities)) {
            builder.withClaim("authorities", authorities);
        }

        //菜单
        if (!CollectionUtils.isEmpty(authorities)) {
            builder.withClaim("menus", menus);
        }

        String token = builder.withClaim("username", username)
                .sign(Algorithm.HMAC256(reportProperties.getSecurity().getJwtSecret()));

        return token;
    }

    /**
     * 获取jwt的负载
     * @param token
     * @return
     */
    public Map<String, Claim> getClaim(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(reportProperties.getSecurity().getJwtSecret())).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaims();
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public String getUsername(String token) {
        Claim claim = getClaim(token).get("username");
        if(claim == null) {
            return null;
        }
        return claim.asString();
    }


    /**
     * 获取租户
     * @param token
     * @return
     */
    public String getTenant(String token) {
        Claim claim = getClaim(token).get("tenant");
        if(claim == null) {
            return null;
        }
        return claim.asString();
    }

    /**
     * 获取用户类型
     * @param token
     * @return
     */
    public Integer getUserType(String token) {
        Claim claim = getClaim(token).get("type");
        if(claim == null) {
            return null;
        }
        return claim.asInt();
    }

    /**
     * 获取客户端唯一标识
     * @param token
     * @return
     */
    public String getUUID(String token) {
        Claim claim = getClaim(token).get("uuid");
        if(claim == null) {
            return null;
        }
        return claim.asString();
    }
}
