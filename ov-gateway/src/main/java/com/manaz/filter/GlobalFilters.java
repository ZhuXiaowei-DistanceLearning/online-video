package com.manaz.filter;

import com.manaz.exception.BaseException;
import com.manaz.utils.JwtUtils;
import com.manaz.vo.JsonResult;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author zxw
 * @date 2020/7/18 13:03
 */
@Component
public class GlobalFilters implements GlobalFilter {
    public static final String[] WHITE_LIST = {"/login"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route gatewayUri = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        URI uri = gatewayUri.getUri();
        ServerHttpRequest request = exchange.getRequest();
        // 请求路径
        RequestPath requestPath = exchange.getRequest().getPath();
        for (String path : WHITE_LIST) {
            if (path.equals(requestPath.toString())) {
                return chain.filter(exchange);
            }
        }
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(JwtUtils.HEADER_AUTH);
        Object data = JwtUtils.verifyToken(token).getData();
        ServerHttpRequest.Builder mutate = request.mutate();
        if (data == null) {
            throw new BaseException(JsonResult.error("token已过期，请重新登录"));
        }
//        List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");
//        if (authorization.size() == 0) {
//            throw new BaseException(ExceptionEnums.USER_OR_PASSWORD_ERROR);
//        }
//        Boolean b = JwtUtils.verifyToken(authorization.get(0));
//        return b ? chain.filter(exchange) : exchange.getResponse().setComplete();
        mutate.header("x-user-serviceName", uri.getHost());
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
