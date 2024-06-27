package com.ablecisi.contrller;

import com.ablecisi.SpringbootWebManagementApplication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.jfr.SettingDescriptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest(classes = SpringbootWebManagementApplication.class)
class SpringbootWebManagementApplicationTests {

    /* @Test
    void contextLoads() {
        for (int i = 0; i < 1001; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println("第"+ i +"个uuid = " + uuid);
        }
    } */

    /**
     *
     * 测试生成jwt
     */
    /* @Test
    void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name", "ablecisi");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "ablecisi")// 签名算法
                .setClaims(claims) // 自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000)) // JWt有效期时间
                .compact();
        System.out.println(jwt);
    } */

    /* @Test
    void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("ablecisi")
                .parseClaimsJws("")
                .getBody();

        System.out.println(claims);
    } */



}
