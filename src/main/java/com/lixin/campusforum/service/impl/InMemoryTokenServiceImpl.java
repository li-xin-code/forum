package com.lixin.campusforum.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.lixin.campusforum.common.exception.TokenInvalidException;
import com.lixin.campusforum.model.vo.user.UserVo;
import com.lixin.campusforum.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * token 内存存储
 *
 * @author lixin
 */
@Service("inMemoryTokenService")
public class InMemoryTokenServiceImpl implements TokenService<UserVo> {

    private final Cache<String, UserVo> tokenCache = CacheBuilder.newBuilder()
            .maximumSize(1000L)
            .expireAfterAccess(20, TimeUnit.MINUTES)
            .build();

    @Override
    public String getToken(UserVo data) {
        String token = generateToken();
        tokenCache.put(token, data);
        return token;
    }

    @Override
    public UserVo getData(String token) {
        return Optional.ofNullable(tokenCache.getIfPresent(token))
                .orElseThrow(TokenInvalidException::new);
    }

    @Override
    public void removeToken(String token) {
        tokenCache.invalidate(token);
    }

    @Override
    public void update(String token, UserVo data) {
        tokenCache.put(token, data);
    }

    private String generateToken() {
        String random = String.valueOf((System.currentTimeMillis() +
                new Random().nextInt(8888)));
        return DigestUtils.md5DigestAsHex(random.getBytes(StandardCharsets.UTF_8));
    }

}
