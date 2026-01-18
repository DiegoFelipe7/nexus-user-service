package co.com.nexus.s3.config;

import co.com.nexus.s3.config.model.S3ConnectionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.metrics.MetricPublisher;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;


import java.net.URI;

@Configuration
public class S3Config {
    @Profile("production")
    @Bean
    public S3AsyncClient s3AsyncClient(S3ConnectionProperties s3Properties, MetricPublisher publisher) {
        return S3AsyncClient.builder()
                .overrideConfiguration(o -> o.addMetricPublisher(publisher))
                .region(Region.of(s3Properties.region()))
                .credentialsProvider(() -> AwsBasicCredentials.create(s3Properties.accessKey(), s3Properties.secretKey()))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner(S3ConnectionProperties properties) {
        return S3Presigner.builder()
                .region(Region.of(properties.region()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        properties.accessKey(),
                                        properties.secretKey()
                                )
                        )
                )
                .build();
    }

    @Profile("local")
    @Bean
    public S3AsyncClient localS3AsyncClient(S3ConnectionProperties s3Properties,
                                            MetricPublisher publisher) {
        return S3AsyncClient.builder()
                .overrideConfiguration(o -> o.addMetricPublisher(publisher))
                .region(Region.of(s3Properties.region()))
                .credentialsProvider(() -> AwsBasicCredentials.create(s3Properties.accessKey(), s3Properties.secretKey()))
                .endpointOverride(URI.create(s3Properties.endpoint()))
                .build();
    }

}
