package com.example.ch05_webflux.filter;

import com.example.ch05_webflux.auth.IamAuthentication;
import com.example.ch05_webflux.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * 빈으로 등록되어있으면 필터 수행됨
 */
@RequiredArgsConstructor
@Component
public class SecurityWebFilter implements WebFilter  {
    private final AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final ServerHttpResponse resp = exchange.getResponse();

        // 요청으로부터 헤더 꺼내기
        String iam = exchange.getRequest().getHeaders()
                .getFirst("X-I-AM");

        if (iam == null) {
            resp.setStatusCode(HttpStatus.UNAUTHORIZED);
            return resp.setComplete(); // 아무런 값 반환하지 않고 complete
        }

        return authService.getNameByToken(iam)
                .map(IamAuthentication::new)
                .flatMap(authentication -> {
                    return chain.filter(exchange)
                            .contextWrite(context -> { // context를 받음
                                // 다음 Context 만들기
                                Context newContext = ReactiveSecurityContextHolder
                                        .withAuthentication(authentication);

                                // 값이 있을수도 있으므로 putAll()
                                // context 다음으로 신규 Context 연결
                                return context.putAll(newContext);
                            });
                })
                .switchIfEmpty(Mono.defer(() -> {
                    resp.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return resp.setComplete();
                }));

    }
}
