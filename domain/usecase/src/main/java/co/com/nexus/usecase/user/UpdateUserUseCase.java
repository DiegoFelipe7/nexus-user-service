package co.com.nexus.usecase.user;

import co.com.nexus.model.shared.constants.HttpStatus;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateUserUseCase implements BiFunction<UUID, UserModel, Mono<UserModel>> {
    private final UserRepository userRepository;
    @Override
    public Mono<UserModel> apply(UUID uuid, UserModel userModel) {
        return userRepository.findById(uuid)
                .switchIfEmpty(Mono.error(new NexusException("USUARIO NO ENCONTRADO" , HttpStatus.FORBIDDEN)))
                .flatMap(ele-> {
                    userModel.setId(ele.getId());
                    return userRepository.saveUser(userModel);
                });

    }
}
