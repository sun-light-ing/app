package com.song.app.aspect;

import com.song.app.annotation.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
/**
 * @BelongsProject: appBack
 * @BelongsPackage: com.song.app.aspect
 * @Author: sun
 * @CreateTime: 2024-12-08  20:12
 * @Description: TODO
 * @Version: 1.0
 */
@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Around("@annotation(rateLimit)")
    public Object limitRequest(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        String redisKey = "rate_limit:" + methodName;

        // 检查 Redis 中是否已有记录
        Boolean isAllowed = redisTemplate.opsForValue().setIfAbsent(redisKey, "1", rateLimit.seconds(), TimeUnit.SECONDS);

        if (Boolean.TRUE.equals(isAllowed)) {
            // 如果没有记录，允许执行方法
            return joinPoint.proceed();
        } else {
            // 如果已有记录，拒绝请求
            return "请求过于频繁，请稍后再试";
        }
    }
}