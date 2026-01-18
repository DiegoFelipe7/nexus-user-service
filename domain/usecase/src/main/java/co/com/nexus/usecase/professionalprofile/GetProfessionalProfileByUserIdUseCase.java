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
public class GetProfessionalProfileByUserIdUseCase implements Function<UUID, Mono<ProfessionalProfileModel>> {
    private final ProfessionalProfileRepository professionalProfileRepository;

    @Override
    public Mono<ProfessionalProfileModel> apply(UUID userId) {
        return professionalProfileRepository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new NexusException("PERFIL PROFESIONAL NO ENCONTRADO PARA ESTE USUARIO", HttpStatusConstants.NOT_FOUND)));
    }
}
