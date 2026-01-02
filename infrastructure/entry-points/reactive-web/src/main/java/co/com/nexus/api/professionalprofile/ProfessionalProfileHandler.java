package co.com.nexus.api.professionalprofile;

import co.com.nexus.api.config.ObjectValidator;
import co.com.nexus.api.professionalprofile.dto.ProfessionalProfileRequest;
import co.com.nexus.api.professionalprofile.mapper.ProfessionalProfileMapper;
import co.com.nexus.model.professionalprofile.ProfessionalProfileModel;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.usecase.professionalprofile.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProfessionalProfileHandler {
    private final GetProfessionalProfileByIdUseCase getProfessionalProfileByIdUseCase;
    private final GetProfessionalProfileByUserIdUseCase getProfessionalProfileByUserIdUseCase;
    private final CreateProfessionalProfileUseCase createProfessionalProfileUseCase;
    private final UpdateProfessionalProfileUseCase updateProfessionalProfileUseCase;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> getProfessionalProfileById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getProfessionalProfileByIdUseCase.apply(UUID.fromString(id)), ProfessionalProfileModel.class);
    }

    public Mono<ServerResponse> getProfessionalProfileByUserId(ServerRequest request) {
        String userId = request.pathVariable("userId");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getProfessionalProfileByUserIdUseCase.apply(UUID.fromString(userId)), ProfessionalProfileModel.class);
    }

    public Mono<ServerResponse> createProfessionalProfile(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(ProfessionalProfileRequest.class)
                .flatMap(objectValidator::validate)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createProfessionalProfileUseCase.apply(ProfessionalProfileMapper.mapToModel(ele, UUID.fromString(id))), ProfessionalProfileModel.class));
    }

    public Mono<ServerResponse> updateProfessionalProfile(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(ProfessionalProfileRequest.class)
                .flatMap(objectValidator::validate)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateProfessionalProfileUseCase.apply(ProfessionalProfileMapper.mapToModel(ele, UUID.fromString(id))), ProfessionalProfileModel.class));
    }
}
