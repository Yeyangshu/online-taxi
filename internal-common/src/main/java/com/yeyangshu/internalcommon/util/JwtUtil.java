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
     * 生成jwt
     *
     * @param subject
     * @param issueDate
     * @return
     */
    public static String createJavaWebToken(String subject, Date issueDate) {
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issueDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
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
                JwtInfo ji = new JwtInfo();
                ji.setSubject(claims.getSubject());
                ji.setIssueDate(claims.getIssuedAt().getTime());
                return ji;
            }
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            System.out.println("jwt过期了");
        }
        return null;
    }
}