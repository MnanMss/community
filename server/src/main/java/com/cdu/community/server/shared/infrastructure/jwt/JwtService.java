package com.cdu.community.server.shared.infrastructure.jwt;


import com.cdu.community.server.shared.infrastructure.util.RandomUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {

    // 用来为 JWT 签名的字符串（可以是一个密钥字符串
    private final String sign;
    // 通过密钥生成的 SecretKey 实例，用于加密和解密
    private final String secret;
    // JWT 的过期时间，以毫秒计
    private final int expiration;
    // 用于增加加密安全性的随机字符串
    private final String salt;
    //  用于连接加密内容的分隔符
    private final String separator;

    private final String aesSecret;

    public static final String ATTRIBUTE = "Identity";

    /**
     * 构造函数
     *
     * @param sign      签名
     * @param secret    密钥
     * @param expiration 过期时间
     */
    public JwtService(String sign, String secret, int expiration) {
        // 设置签名
        this.sign = sign;
        // 使用秘钥生成 HmacSHA 秘钥
        this.secret = secret;
        // 设置过期时间
        this.expiration = expiration;
        // 生成随机加密字符串
        this.salt = RandomUtil.generateRandomString(256);
        // 设置分隔符
        this.separator = "\n";
        this.aesSecret = secret.substring(0 , 16);
    }

    /**
     * 生成加密的主题
     *
     * @param id 用户ID
     * @return 加密后的主题字符串
     * @throws Exception 如果加密失败
     */
    public String encryptedSubject(String id) throws Exception {
        // 实例化一个AES加密服务，并加密主题字符串
        // 构建主题字符串
        String subject = id + separator + salt;
        // 实例化AES加密服务
        AESEncryptionService aes = new AESEncryptionService(aesSecret);
        // 使用AES加密主题字符串
        String encryptedSubject = aes.encryptString(subject);
        // 如果加密后的主题字符串为空，则抛出异常
        if (encryptedSubject == null) {
            throw new Exception("encrypt subject in jwt");
        }
        // 返回加密后的主题字符串
        return encryptedSubject;

    }

    /**
     * 解密主题
     *
     * @param encryptedSubject 加密的主题字符串
     * @return 解密后的用户ID
     * @throws Exception 如果解密失败
     */
    public String decryptedSubject(String encryptedSubject) throws Exception {
        // 实例化一个AES解密服务，并解密主题字符串
        AESEncryptionService aes = new AESEncryptionService(aesSecret);
        String subject = aes.decryptString(encryptedSubject);
        if (subject == null) {
            throw new Exception("decrypt subject in jwt");
        }
        String[] parts = subject.split(separator);
        if (parts.length != 2) {
            throw new Exception("invalid subject in jwt");
        }
        return parts[0];
    }

    /**
     * 生成JWT Token
     *
     * @param id 用户ID
     * @return JWT Token
     * @throws Exception 如果生成失败
     */
    public String generateToken(String id) throws Exception {
        // 构建JWT Token
        // 创建一个JWT Token并返回
        // 创建一个空的声明（payload）部分
        Map<String, Object> claims = new HashMap<>();
        // 对id进行加密，作为JWT Token的主题
        String subject = encryptedSubject(id);
        return Jwts.builder()
            // 设置声明（payload）部分
            .setClaims(claims)
            // 设置主题
            .setSubject(subject)
            // 设置JWT Token的签发时间
            .setIssuedAt(new Date(System.currentTimeMillis()))
            // 设置JWT Token的过期时间
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            // 使用指定的算法和密钥对JWT Token进行签名
            .signWith(SignatureAlgorithm.HS512, secret)
            // 生成最终的JWT Token并返回
            .compact();
    }

    /**
     * 将JWT Token注入响应头
     *
     * @param response HTTP响应
     * @param token    JWT Token
     */
    public void injectToken(HttpServletResponse response, String token) {
        // 在HTTP响应头中设置JWT Token
        response.setHeader("Authorization", "Bearer " + token);
    }

    /**
     * 从请求头中提取JWT Token
     *
     * @param request HTTP请求
     * @return 提取的JWT Token
     */
    public String extractToken(HttpServletRequest request) {
        // 从HTTP请求头中提取JWT Token
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    /**
     * 从JWT Token中提取用户ID
     *
     * @param token JWT Token
     * @return 用户ID
     * @throws Exception 如果提取失败
     */
    public String extractId(String token) throws Exception {
        // 从JWT Token中解析出加密的主题，并解密得到用户ID
        String encryptedSubject = extractClaim(token, Claims::getSubject);
        return decryptedSubject(encryptedSubject);
    }

    /**
     * 从JWT Token中提取过期时间
     *
     * @param token JWT Token
     * @return 过期时间
     */
    public Date extractExpirationDate(String token) {
        // 从JWT Token中解析出过期时间
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 从JWT Token中提取指定的Claims信息
     *
     * @param token            JWT Token
     * @param claimsResolver   Claims解析函数
     * @param <T>              泛型类型
     * @return 提取的Claims信息
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // 解析JWT Token，并根据传入的解析函数解析出指定的Claims信息
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 提取所有的JWT Claims
     *
     * @param token JWT Token
     * @return 提取的Claims信息
     */
    public Claims extractAllClaims(String token) {
        // 解析JWT Token，提取所有的Claims信息
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 判断Token是否过期
     *
     * @param token JWT Token
     * @return 如果过期返回true，否则返回false
     */
    public Boolean isTokenExpired(String token) {
        // 检查JWT Token是否过期
        final Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }

    /**
     * 验证JWT Token
     *
     * @param token JWT Token
     * @param id    用户ID
     * @return 如果验证通过返回true，否则返回false
     * @throws Exception 如果验证失败
     */
    public Boolean validateToken(String token, String id) throws Exception {
        // 验证JWT Token的有效性
        final String tokenId = extractId(token);
        return (tokenId.equals(id) && !isTokenExpired(token));
    }
}
