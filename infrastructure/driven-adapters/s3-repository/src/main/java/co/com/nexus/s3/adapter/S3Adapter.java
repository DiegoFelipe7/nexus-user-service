package co.com.nexus.s3.adapter;

import co.com.nexus.model.s3.gateway.S3Repository;
import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.s3.config.model.S3ConnectionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import co.com.nexus.s3.operations.S3Operations;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Repository
@AllArgsConstructor
public class S3Adapter implements S3Repository {

    private final S3Operations s3Operations;
    private final S3ConnectionProperties s3ConnectionProperties;

    @Override
    public Mono<String> uploadUserAvatar(String objectKey, byte[] file) {
        return s3Operations.uploadObject(s3ConnectionProperties.bucketName(), objectKey, file)
                .flatMap(image -> {
                    if (!image) {
                        return Mono.error(new NexusException("Failed to upload avatar to S3", HttpStatusConstants.BAD_REQUEST));

                    }
                    return Mono.just(objectKey);
                });
    }


    @Override
    public Mono<String> generatePresignedUrl(String objectKey, Duration expiration) {
        return s3Operations.generatePresignedUrl(
                s3ConnectionProperties.bucketName(),
                objectKey,
                expiration
        ).doOnNext(url ->
                log.info("Presigned URL generated successfully | key={} | url={}", objectKey, url)
        ).doOnError(error -> {
            log.error("Error generating presigned URL for key={} : {}", objectKey, error.getMessage());
        });
    }

    @Override
    public Mono<Void> deleteFile(String objectKey) {
        return s3Operations.deleteObject(s3ConnectionProperties.bucketName(), objectKey)
                .doOnSuccess(unused -> log.info("File deleted successfully from S3 | key={}", objectKey))
                .doOnError(error -> log.error("Error deleting file from S3 | key={} | error={}", objectKey, error.getMessage()))
                .then();
    }
}
