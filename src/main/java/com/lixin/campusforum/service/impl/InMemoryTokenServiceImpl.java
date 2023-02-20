package com.lixin.campusforum.service.impl;

import com.lixin.campusforum.common.exception.TokenInvalidException;
import com.lixin.campusforum.model.vo.UserVo;
import com.lixin.campusforum.service.TokenService;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author lixin
 */
@Service("inMemoryTokenService")
public class InMemoryTokenServiceImpl implements TokenService<UserVo>, DisposableBean {

    private final Map<String, UserVo> map;
    private final ScheduledExecutorService executorService;
    private final Map<String, Future<?>> futures;

    public InMemoryTokenServiceImpl() {
        ThreadFactory threadFactory = new BasicThreadFactory
                .Builder().namingPattern("token-service-schedule-pool-%d").daemon(true).build();
        this.executorService = new ScheduledThreadPoolExecutor(10, threadFactory);
        this.map = new ConcurrentHashMap<>(16);
        this.futures = new ConcurrentHashMap<>(16);
    }

    @Override
    public String getToken(UserVo data) {
        if (map.containsValue(data)) {
            for (String t : map.keySet()) {
                if (map.get(t).equals(data)) {
                    return t;
                }
            }
        }
        String token = generateToken();
        map.put(token, data);
        ScheduledFuture<?> future = executorService.schedule(() -> {
            map.remove(token);
        }, 15L, TimeUnit.DAYS);
        futures.put(token, future);
        return token;
    }

    @Override
    public UserVo getData(String token) {
        UserVo user = map.get(token);
        if (user == null) {
            throw new TokenInvalidException();
        }
        return user;
    }

    @Override
    public void removeToken(String token) {
        map.remove(token);
        Future<?> future = futures.get(token);
        future.cancel(true);
    }

    @Override
    public void update(String token, UserVo data) {
        map.put(token, data);
    }

    private String generateToken() {
        String random = String.valueOf((System.currentTimeMillis() +
                new Random().nextInt(8888)));
        return DigestUtils.md5DigestAsHex(random.getBytes(StandardCharsets.UTF_8));
    }


    @Override
    public void destroy() {
        executorService.shutdownNow();
    }

}
