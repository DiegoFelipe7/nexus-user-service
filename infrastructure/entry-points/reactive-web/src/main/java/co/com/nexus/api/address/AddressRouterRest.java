package co.com.nexus.api.address;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AddressRouterRest {
    @Bean
    public RouterFunction<ServerResponse> addressRouterFunction(AddressHandler handler) {
        return route(GET("addresses"), handler::getAddressesByUserId)
                .and(route(GET("addresses/{id}"), handler::getAddressById))
                .and(route(POST("addresses"), handler::createAddress))
                .and(route(PUT("addresses/{id}"), handler::updateAddress))
                .and(route(DELETE("addresses/{id}"), handler::deleteAddress));
    }
}
