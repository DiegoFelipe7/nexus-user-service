package co.com.nexus.s3.adapter;

import co.com.nexus.model.user.gateways.S3Repository;
import org.springframework.stereotype.Repository;
import co.com.nexus.s3.operations.S3Operations;
    import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class S3Adapter  implements S3Repository {

   private final S3Operations s3Operations;

    @Override
    public Mono<String> uploadUserAvatar(byte[] avatarContent) {
        return s3Operations.uploadObject("","",avatarContent);
    }
}
