package co.com.nexus.api.patient;

import co.com.nexus.api.config.ObjectValidator;
import co.com.nexus.api.patient.dto.PatientRequest;
import co.com.nexus.api.patient.mapper.PatientMapper;
import co.com.nexus.api.shared.mapper.QueryParamsMapper;
import co.com.nexus.model.patient.model.PatientModel;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.usecase.patient.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class PatientHandler {
    private final CreatePatientUseCase createPatientUseCase;
    private final GetPatientByIdUseCase getPatientByIdUseCase;
    private final GetAllPatientsUseCase getAllPatientsUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final DeletePatientUseCase deletePatientUseCase;
    private final GetPatientByDocumentUseCase getPatientByDocumentUseCase;
    private final ObjectValidator validator;

    public Mono<ServerResponse> createPatient(ServerRequest request) {
        return request.bodyToMono(PatientRequest.class)
                .flatMap(validator::validate)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createPatientUseCase.apply(PatientMapper.toModel(ele)), PatientModel.class));
    }

    public Mono<ServerResponse> getPatientById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getPatientByIdUseCase.apply(id), PatientModel.class);
    }

    public Mono<ServerResponse> getAllPatients(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllPatientsUseCase.apply(QueryParamsMapper.mapToQueryParams(request)), PagingResult.class);

    }

    public Mono<ServerResponse> updatePatient(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(PatientRequest.class)
                .flatMap(validator::validate)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updatePatientUseCase.apply(id, PatientMapper.toModel(ele)), PatientModel.class));
    }

    public Mono<ServerResponse> deletePatient(ServerRequest request) {
        String id = request.pathVariable("id");
        return deletePatientUseCase.apply(id)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getPatientByDocument(ServerRequest request) {
        String documentNumber = request.pathVariable("documentNumber");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getPatientByDocumentUseCase.execute(documentNumber), PatientModel.class);

    }


}
