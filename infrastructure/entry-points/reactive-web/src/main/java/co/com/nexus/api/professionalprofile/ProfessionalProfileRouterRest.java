package co.com.nexus.api.professionalprofile;

import co.com.nexus.api.filters.AuthHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProfessionalProfileRouterRest {
    @Bean
    public RouterFunction<ServerResponse> professionalProfileRouterFunction(ProfessionalProfileHandler handler, AuthHeaderFilter filter) {
        return route(GET("professional-profiles/{id}"), handler::getProfessionalProfileById)
                .and(route(GET("professional-profiles"), handler::getProfessionalProfileByUserId).filter(filter))
                .and(route(POST("professional-profiles"), handler::createProfessionalProfile))
                .and(route(PATCH("professional-profiles/{id}"), handler::updateProfessionalProfile));
    }
}
