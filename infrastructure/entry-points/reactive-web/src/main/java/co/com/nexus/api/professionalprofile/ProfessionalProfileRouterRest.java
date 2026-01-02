package co.com.nexus.api.professionalprofile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProfessionalProfileRouterRest {
    @Bean
    public RouterFunction<ServerResponse> professionalProfileRouterFunction(ProfessionalProfileHandler handler) {
        return route(GET("professional-profiles/{id}"), handler::getProfessionalProfileById)
                .and(route(GET("professional-profiles/user/{userId}"), handler::getProfessionalProfileByUserId))
                .and(route(POST("professional-profiles"), handler::createProfessionalProfile))
                .and(route(PATCH("professional-profiles/{id}"), handler::updateProfessionalProfile));
    }
}
