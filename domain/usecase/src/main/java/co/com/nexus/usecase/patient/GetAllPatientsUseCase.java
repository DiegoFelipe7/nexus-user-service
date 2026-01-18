package co.com.nexus.usecase.patient;

import co.com.nexus.model.patient.gateways.PatientRepository;
import co.com.nexus.model.patient.model.PatientModel;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllPatientsUseCase implements Function<QueryParams, Mono<PagingResult<PatientModel>>> {
    private final PatientRepository patientRepository;
    
    @Override
    public Mono<PagingResult<PatientModel>> apply(QueryParams queryParams) {
        return patientRepository.findAll(queryParams);
    }
}
