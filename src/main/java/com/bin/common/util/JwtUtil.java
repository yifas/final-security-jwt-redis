package com.bin.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 已经没用了！！
 */
public class JwtUtil {
    //有效期为
    public static final Long JWT_TTL = 3600000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "bing";

    /**
     * 生成token
     * @param subject （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims 自定义身份信息
     * @return
     */
    public static String generateToken(String subject, int expirationSeconds, Map<String, Object> claims) {

        SecretKey secretKey = generalKey();

        return Jwts.builder()
            // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
             //Map<String, Object> claims = new HashMap<>();
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000 * 24 * 60 * 60))
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(SignatureAlgorithm.RS256, secretKey)
                //.signWith(SignatureAlgorithm.RS256, JWT_KEY)
                .compact();
    }
    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                //.setIssuer("admin")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     * // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
     * // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        //改AES
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();//签名秘钥，和生成的签名的秘钥一模一样
        return Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(secretKey)
                //设置需要解析的jwt
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 解析token,获得subject中的信息
     */
    public static String parseToken(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
       SecretKey secretKey = generalKey();
        return Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(secretKey)
                //设置需要解析的jwt
                .parseClaimsJws(token)
                .getBody().getSubject();

    }



    /**
     * 是否已过期
     * @return true: 令牌过期, false: 令牌没过期
     */
    public static boolean isExpiration(String expireTime) {
        //通过redis中的失效时间进行判断
        String currentTime = DateUtil.getTime();
        // 当前时间比过期时间小
        return DateUtil.compareDate(currentTime, expireTime);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generalKey());
        }
    }
}
