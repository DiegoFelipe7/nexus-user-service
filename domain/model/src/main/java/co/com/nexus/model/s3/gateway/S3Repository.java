package co.com.nexus.model.s3.gateway;


import reactor.core.publisher.Mono;

import java.time.Duration;


public interface S3Repository {
    Mono<String> uploadUserAvatar(String objectKey, byte[] file);
    Mono<String> generatePresignedUrl(String objectKey, Duration expiration);
    Mono<Void> deleteFile(String objectKey);
}
