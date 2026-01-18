package co.com.nexus.r2dbc.professionalprofile;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ProfessionalProfileReactiveRepository extends ReactiveCrudRepository<ProfessionalProfile, UUID>,
        ReactiveQueryByExampleExecutor<ProfessionalProfile>,
        ReactiveSortingRepository<ProfessionalProfile, UUID> {
    
    Mono<ProfessionalProfile> findByUserId(UUID userId);
}
