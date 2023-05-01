package com.lixin.campusforum.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.lixin.campusforum.common.exception.TokenInvalidException;
import com.lixin.campusforum.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * token 内存存储
 *
 * @author lixin
 */
@Service("inMemoryTokenService")
public class InMemoryTokenServiceImpl implements TokenService<String> {

    private final Cache<String, String> tokenCache = CacheBuilder.newBuilder()
            .maximumSize(1000L)
            .expireAfterAccess(20, TimeUnit.MINUTES)
            .build();

    @Override
    public String getToken(String data) {
        String token = generateToken();
        tokenCache.put(token, data);
        return token;
    }

    @Override
    public String getData(String token) {
        return Optional.ofNullable(tokenCache.getIfPresent(token))
                .orElseThrow(TokenInvalidException::new);
    }

    @Override
    public void removeToken(String token) {
        tokenCache.invalidate(token);
    }

    @Override
    public Boolean contain(String token) {
        return Objects.nonNull(tokenCache.getIfPresent(token));
    }

    private String generateToken() {
        String random = String.valueOf((System.currentTimeMillis() +
                new Random().nextInt(8888)));
        return DigestUtils.md5DigestAsHex(random.getBytes(StandardCharsets.UTF_8));
    }

}
