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
        return route(GET("users"), handler::getAllUsers)
                .and(route(GET("users/{id}"), handler::getUserById))
                .and(route(PATCH("users/{id}"), handler::updateUser))
                .and(route(PATCH("users/{id}/avatar"), handler::updateUserAvatar));
    }
}
