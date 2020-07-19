package com.manaz.filter;

import com.manaz.enums.ExceptionEnums;
import com.manaz.exception.BaseException;
import com.manaz.utils.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zxw
 * @date 2020/7/18 13:03
 */
@Component
public class GlobalFilters implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");
        if (authorization.size() == 0) {
            throw new BaseException(ExceptionEnums.USER_OR_PASSWORD_ERROR);
        }
        Boolean b = JwtUtils.verifyToken(authorization.get(0));
        return b ? chain.filter(exchange) : exchange.getResponse().setComplete();
    }
}
