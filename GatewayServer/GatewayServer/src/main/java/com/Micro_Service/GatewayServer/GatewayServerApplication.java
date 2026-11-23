package com.Micro_Service.GatewayServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
		System.out.println("Gate-way Server is running:");
	}
  @Bean
  public RouteLocator microServiceRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p->p.path("/microservicepractice/userservice/**")
						.filters(f->f.rewritePath("/microservicepractice/userservice/(?<segment>.*)","/${segment}"))
						.uri("lb://USERSERVICE"))
                 // retry,circuitBreaker and rate-Limiter pattern at Gateway Server level
				.route(p->p.path("/microservicepractice/paymentservice/**")
						.filters(f->f.rewritePath("/microservicepractice/paymentservice/(?<segment>.*)","/${segment}")
										.retry(retryConfig -> retryConfig.setRetries(3).setMethods(HttpMethod.GET)
												.setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
								        .circuitBreaker(config -> config.setName("PaymentServiceCiruitBreaker")
										   .setFallbackUri("forward:/fallBackMethod"))
										.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
												.setKeyResolver(userKeyResolver())))
						.uri("lb://PAYMENTSERVICE"))

				.route(p->p.path("/microservicepractice/cartservice/**")
						.filters(f->f.rewritePath("/microservicepractice/cartservice/(?<segment>.*)","/${segment}"))
						.uri("lb://CARTSERVICE"))

				.route(p->p.path("/microservicepractice/productservice/**")
						.filters(f->f.rewritePath("/microservicepractice/productservice/(?<segment>.*)","/${segment}"))
						.uri("lb://PRODUCTSERVICE"))

				.build();
	}
  @Bean
public RedisRateLimiter redisRateLimiter(){
		return new RedisRateLimiter(1,1,1);
  }
	@Bean
	KeyResolver  userKeyResolver(){
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous");
	}

}
