package co.com.nexus.usecase.user;

import co.com.nexus.model.shared.constants.HttpStatus;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.model.user.gateways.S3Repository;
import co.com.nexus.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.BiFunction;


@RequiredArgsConstructor
public class UpdateUserAvatarUseCase implements BiFunction<UUID,  byte[], Mono<String>> {

    private final UserRepository userRepository;
    private final S3Repository s3Repository;

    @Override
    public Mono<String> apply(UUID uuid, byte[] avatarFile) {
        return s3Repository.uploadUserAvatar(avatarFile)
                .flatMap(ele -> userRepository.findById(uuid)
                        .switchIfEmpty(Mono.error(new NexusException("USUARIO NO ENCONTRADO", HttpStatus.FORBIDDEN)))
                        .flatMap(userModel -> {
                            userModel.setAvatarUrl(ele);
                            return userRepository.saveUser(userModel)
                                    .map(UserModel::getAvatarUrl);
                        })
                );
    }
}
