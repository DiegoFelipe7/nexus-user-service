package co.com.nexus.usecase.professionalprofile;

import co.com.nexus.model.professionalprofile.ProfessionalProfileModel;
import co.com.nexus.model.professionalprofile.gateways.ProfessionalProfileRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateProfessionalProfileUseCase implements Function<ProfessionalProfileModel, Mono<ProfessionalProfileModel>> {

    private final ProfessionalProfileRepository professionalProfileRepository;

    @Override
    public Mono<ProfessionalProfileModel> apply(ProfessionalProfileModel professionalProfileModel) {
        return professionalProfileRepository.save(professionalProfileModel);
    }
}
