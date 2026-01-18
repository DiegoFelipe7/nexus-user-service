package co.com.nexus.usecase.patient;

import co.com.nexus.model.patient.gateways.PatientRepository;
import co.com.nexus.model.patient.model.PatientModel;
import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.NexusException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreatePatientUseCase implements Function<PatientModel, Mono<PatientModel>> {
    private final PatientRepository patientRepository;

    @Override
    public Mono<PatientModel> apply(PatientModel patientModel) {
        return patientRepository.findByDocumentNumber(patientModel.getPersonalInfo().getDocumentNumber())
                .flatMap(existing -> Mono.error(new NexusException("EL PACIENTE YA SE ENCUENTRA REGISTRADO", HttpStatusConstants.BAD_REQUEST)))
                .switchIfEmpty(patientRepository.savePatient(patientModel))
                .cast(PatientModel.class);

    }
}
