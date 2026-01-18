package co.com.nexus.usecase.patient;

import co.com.nexus.model.patient.gateways.PatientRepository;
import co.com.nexus.model.patient.model.PatientModel;
import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.NexusException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdatePatientUseCase  implements BiFunction<String, PatientModel, Mono<PatientModel>> {
    private final PatientRepository patientRepository;


    @Override
    public Mono<PatientModel> apply(String id, PatientModel patientModel) {
        return patientRepository.findById(id)
                .switchIfEmpty(Mono.error(new NexusException("EL PACIENTE CON EL ID" + id + " NO SE ENCUENTRA", HttpStatusConstants.NOT_FOUND)))
                .flatMap(existingPatient -> {
                    patientModel.setId(existingPatient.getId());
                    patientModel.setCreatedAt(existingPatient.getCreatedAt());
                    return patientRepository.savePatient(patientModel);
                });
    }
}
