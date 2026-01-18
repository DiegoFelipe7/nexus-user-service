package co.com.nexus.usecase.patient;

import co.com.nexus.model.patient.gateways.PatientRepository;
import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.NexusException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeletePatientUseCase implements Function<String, Mono<Void>> {
    private final PatientRepository patientRepository;

    @Override
    public Mono<Void> apply(String id) {
        return patientRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new NexusException("EL PACIENTE CON EL ID" + id + " NO SE ENCUENTRA" , HttpStatusConstants.NOT_FOUND)))
                .flatMap(patient -> patientRepository.deleteById(id));

    }

}
