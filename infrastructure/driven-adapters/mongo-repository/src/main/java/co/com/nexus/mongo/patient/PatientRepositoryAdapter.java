package co.com.nexus.mongo.patient;

import co.com.nexus.model.patient.gateways.PatientRepository;
import co.com.nexus.model.patient.model.PatientModel;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import co.com.nexus.mongo.helper.AdapterOperations;
import co.com.nexus.mongo.patient.mapper.PatientMapper;
import lombok.extern.log4j.Log4j2;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PatientRepositoryAdapter extends AdapterOperations<PatientModel, Patient, String, PatientDBRepository> implements PatientRepository {

    public PatientRepositoryAdapter(PatientDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, PatientModel.PatientModelBuilder.class).build());
    }


    @Override
    public Mono<PatientModel> savePatient(PatientModel patient) {
        return repository.save(PatientMapper.mapToEntity(patient))
                .map(PatientMapper::mapToModel);
    }

    @Override
    public Mono<PagingResult<PatientModel>> findAll(QueryParams queryParams) {
        Pageable pageable = PageRequest.of(queryParams.getPage(), queryParams.getSize());
        return repository.findAll()
                .collectList()
                .zipWith(repository.count())
                .map(tuple -> new PagingResult<>(
                        tuple.getT1().stream().map(PatientMapper::mapToModel).toList(),
                        queryParams.getPage(),
                        queryParams.getSize(),
                        tuple.getT2(),
                        (int) Math.ceil((double) tuple.getT2() / queryParams.getSize())
                ));
    }

    @Override
    public Flux<PatientModel> findAllByDocumentNumber(String documentNumber) {
        return repository.findByPersonalInfoDocumentNumberStartingWithIgnoreCase(documentNumber)
                .map(PatientMapper::mapToModel);
    }

    @Override
    public Mono<PatientModel> findByDocumentNumber(String documentNumber) {
        return repository.findByPersonalInfoDocumentNumber(documentNumber)
                .map(PatientMapper::mapToModel);
    }
}
