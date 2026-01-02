package co.com.nexus.usecase.professionalprofile;

import co.com.nexus.model.professionalprofile.ProfessionalProfileModel;
import co.com.nexus.model.professionalprofile.gateways.ProfessionalProfileRepository;
import co.com.nexus.model.shared.constants.HttpStatus;
import co.com.nexus.model.shared.exception.NexusException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class UpdateProfessionalProfileUseCase implements Function<ProfessionalProfileModel, Mono<ProfessionalProfileModel>> {
    private final ProfessionalProfileRepository professionalProfileRepository;

    @Override
    public Mono<ProfessionalProfileModel> apply(ProfessionalProfileModel professionalProfileModel) {
        return professionalProfileRepository.findById(professionalProfileModel.getUserId())
                .switchIfEmpty(Mono.error(new NexusException("PERFIL PROFESIONAL NO ENCONTRADO", HttpStatus.NOT_FOUND)))
                .flatMap(existing -> {
                    existing.setSpecialty(professionalProfileModel.getSpecialty());
                    existing.setMedicalLicense(professionalProfileModel.getMedicalLicense());
                    existing.setLicenseCountry(professionalProfileModel.getLicenseCountry());
                    existing.setLicenseExpirationDate(professionalProfileModel.getLicenseExpirationDate());
                    existing.setYearsOfExperience(professionalProfileModel.getYearsOfExperience());
                    existing.setProfessionalBio(professionalProfileModel.getProfessionalBio());
                    return professionalProfileRepository.save(existing);
                });
    }
}
