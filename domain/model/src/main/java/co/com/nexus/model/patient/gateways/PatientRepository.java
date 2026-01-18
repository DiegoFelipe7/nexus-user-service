package co.com.nexus.model.patient.gateways;

import co.com.nexus.model.patient.model.PatientModel;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientRepository {
    Mono<PatientModel> savePatient(PatientModel patient);
    Mono<PatientModel> findById(String id);
    Mono<PagingResult<PatientModel>> findAll(QueryParams queryParams);
    Mono<PatientModel> findByDocumentNumber(String documentNumber);
    Flux<PatientModel> findAllByDocumentNumber(String documentNumber);
    Mono<Void> deleteById(String id);
}
