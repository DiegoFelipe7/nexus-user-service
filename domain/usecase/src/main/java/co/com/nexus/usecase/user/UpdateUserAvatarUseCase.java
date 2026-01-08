package co.com.nexus.usecase.user;

import co.com.nexus.model.s3.FileUploadModel;
import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.model.s3.gateway.S3Repository;
import co.com.nexus.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;
import java.util.function.BiFunction;


@RequiredArgsConstructor
public class UpdateUserAvatarUseCase implements BiFunction<UUID, FileUploadModel, Mono<String>> {

    private final UserRepository userRepository;
    private final S3Repository s3Repository;

    @Override
    public Mono<String> apply(UUID userId, FileUploadModel file) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(
                        new NexusException("USUARIO NO ENCONTRADO", HttpStatusConstants.FORBIDDEN)
                ))
                .flatMap(user ->
                        deletePreviousAvatarIfExists(user)
                                .then(uploadAndUpdateAvatar(user, file))
                );
    }


    private Mono<Void> deletePreviousAvatarIfExists(UserModel user) {
        if (user.getAvatarUrl() == null || user.getAvatarUrl().isBlank()) {
            return Mono.empty();
        }
        return s3Repository.deleteFile(user.getAvatarUrl()).then();
    }

    private Mono<String> uploadAndUpdateAvatar(UserModel user, FileUploadModel file) {
        String objectKey = buildAvatarKey(file.fileName());

        return s3Repository.uploadUserAvatar(objectKey, file.content())
                .then(Mono.defer(() -> {
                    user.setAvatarUrl(objectKey);
                    return userRepository.saveUser(user);
                }))
                .flatMap(savedUser ->
                        s3Repository.generatePresignedUrl(
                                savedUser.getAvatarUrl(),
                                Duration.ofHours(2)
                        )
                );
    }



    private String buildAvatarKey(String originalFileName) {
        String safeFileName = originalFileName
                .toLowerCase()
                .replaceAll("\\s+", "-")
                .replaceAll("[^a-z0-9.\\-]", "");

        return String.format(
                "avatars/%s-%s",
                UUID.randomUUID(),
                safeFileName
        );
    }

}
