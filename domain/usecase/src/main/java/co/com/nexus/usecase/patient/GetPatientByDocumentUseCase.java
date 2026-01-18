package co.com.nexus.usecase.patient;

import co.com.nexus.model.patient.gateways.PatientRepository;
import co.com.nexus.model.patient.model.PatientModel;
import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.NexusException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetPatientByDocumentUseCase {
    private final PatientRepository patientRepository;

    public Mono<PatientModel> execute(String documentNumber) {
        return patientRepository.findByDocumentNumber(documentNumber)
                .switchIfEmpty(Mono.error(
                        new NexusException("PACIENTE NO ENCONTRADO" , HttpStatusConstants.NOT_FOUND)));
    }
}
