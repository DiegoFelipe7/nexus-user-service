package co.com.nexus.model.professionalprofile.gateways;

import co.com.nexus.model.professionalprofile.ProfessionalProfileModel;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProfessionalProfileRepository {
    Mono<ProfessionalProfileModel> save(ProfessionalProfileModel professionalProfileModel);
    Mono<ProfessionalProfileModel> findById(UUID id);
    Mono<ProfessionalProfileModel> findByUserId(UUID userId);
}
