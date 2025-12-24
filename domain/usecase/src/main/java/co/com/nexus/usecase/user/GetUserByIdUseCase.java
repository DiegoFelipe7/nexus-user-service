package co.com.nexus.usecase.user;

import co.com.nexus.model.shared.constants.HttpStatus;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetUserByIdUseCase implements Function<UUID, Mono<UserModel>> {
    private final UserRepository userRepository;

    @Override
    public Mono<UserModel> apply(UUID uuid) {
        return userRepository.findById(uuid)
                .switchIfEmpty(Mono.error(new NexusException("USUARIO NO ENCONTRADO", HttpStatus.FORBIDDEN)));

    }
}
