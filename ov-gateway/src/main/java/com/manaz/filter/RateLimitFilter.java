package com.manaz.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxw
 * @date 2020/7/18 15:20
 */
@Component
public class RateLimitFilter implements GlobalFilter {
    // token补充量
    int refillToken = 10;
    // 补充token时间间隔
    Duration refillDuration = Duration.ofDays(1);
    private static final Map<String, Bucket> LOCAL_CACHE = new ConcurrentHashMap<>();
    // 容量
    int cap = 10;

    private Bucket createNewBucket() {
        Refill refill = Refill.of(refillToken, refillDuration);
        Bandwidth limit = Bandwidth.classic(cap, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = LOCAL_CACHE.computeIfAbsent(ip, k -> createNewBucket());
        System.out.println(bucket.getAvailableTokens());
        if (bucket.tryConsume(1)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
    }
}
