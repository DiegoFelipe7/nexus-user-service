package co.com.nexus.mongo.patient;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientDBRepository extends ReactiveMongoRepository<Patient, String>, ReactiveQueryByExampleExecutor<Patient> {
    Flux<Patient> findByPersonalInfoDocumentNumberStartingWithIgnoreCase(String value);
    Mono<Patient> findByPersonalInfoDocumentNumber(String documentNumber);
}
