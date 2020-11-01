package com.yeyangshu.internalcommon.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt加解密
 *
 * @author yeyangshu
 */
public class JwtUtil {

    private static final String SECRET = "ko346134h_we]rg3in_yip1!";

    /**
     * token有效期 1天
     */
    private static final Integer EXP_HOURS = 24;

    /**
     * 生成jwt
     *
     * @param subject
     * @param issueDate
     * @return
     */
    public static String createJavaWebToken(String subject, Date issueDate) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issueDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解密jwt
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static JwtInfo parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            if (claims != null) {
                JwtInfo jwtInfo = new JwtInfo();
                jwtInfo.setSubject(claims.getSubject());
                jwtInfo.setIssueDate(claims.getIssuedAt().getTime());
                return jwtInfo;
            }
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            System.out.println("jwt过期了");
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(createJavaWebToken("1_15944444444_1", new Date()));
        System.out.println(parseToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxXzE1OTQ0NDQ0NDQ0XzEiLCJpYXQiOjE2MDQxOTA1Mjd9.OoZ_HCm-tw0LJGUsZK5ZH66IlCbz_7pDEDXB1XLzHPyQz2oPnxwiS11GuVBz0oJsrFPMdtiRfvAlofpXrKi3gg"));
    }
}