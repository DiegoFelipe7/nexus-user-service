package co.com.nexus.usecase.professionalprofile;

import co.com.nexus.model.professionalprofile.ProfessionalProfileModel;
import co.com.nexus.model.professionalprofile.gateways.ProfessionalProfileRepository;
import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.NexusException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetProfessionalProfileByIdUseCase implements Function<UUID, Mono<ProfessionalProfileModel>> {
    private final ProfessionalProfileRepository professionalProfileRepository;

    @Override
    public Mono<ProfessionalProfileModel> apply(UUID uuid) {
        return professionalProfileRepository.findById(uuid)
                .switchIfEmpty(Mono.error(new NexusException("PERFIL PROFESIONAL NO ENCONTRADO", HttpStatusConstants.NOT_FOUND)));
    }
}
