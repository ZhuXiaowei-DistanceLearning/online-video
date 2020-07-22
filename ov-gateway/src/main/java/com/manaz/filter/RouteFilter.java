package com.manaz.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 路由耗时统计
 *
 * @author zxw
 * @date 2020/7/18 12:51
 */
public class RouteFilter implements GatewayFilter {
    private static Logger logger = LoggerFactory.getLogger(RouteFilter.class);
    private static final String COUNT_TIME = "count-time";

    public Mono<Void> filter(final ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(COUNT_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(COUNT_TIME);
                    Long endTime = System.currentTimeMillis() - startTime;
                    if (startTime != null) {
                        logger.info(exchange.getRequest().getURI().getRawPath() + ":" + endTime + "ms");
                    }
                })
        );
    }

}
