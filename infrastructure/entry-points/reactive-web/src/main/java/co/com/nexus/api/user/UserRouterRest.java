package co.com.nexus.api.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler handler) {
        return route(GET("api/v1/users"), handler::getAllUsers)
                .and(route(GET("api/v1/users/{id}"), handler::getUserById))
                .and(route(POST("api/v1/users"), handler::createUser))
                .and(route(PATCH("api/v1/users/{id}"), handler::updateUser))
                .and(route(PATCH("api/v1/users/{id}/avatar"), handler::updateUserAvatar));
    }
}
