package co.com.nexus.model.user.gateways;

import reactor.core.publisher.Mono;

public interface S3Repository {
    Mono<String> uploadUserAvatar(byte[] avatarContent);
}
