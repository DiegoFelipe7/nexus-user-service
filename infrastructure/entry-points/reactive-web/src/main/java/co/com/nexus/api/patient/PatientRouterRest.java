package co.com.nexus.api.patient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PatientRouterRest {

    @Bean
    public RouterFunction<ServerResponse> patientRoutes(PatientHandler handler) {
        return route(POST("patients"), handler::createPatient)
                .andRoute(GET("patients/{id}"), handler::getPatientById)
                .andRoute(GET("patients"), handler::getAllPatients)
                .andRoute(PUT("patients/{id}"), handler::updatePatient)
                .andRoute(DELETE("patients/{id}"), handler::deletePatient)
                .andRoute(GET("patients/document/{documentNumber}"), handler::getPatientByDocument);
    }
}
