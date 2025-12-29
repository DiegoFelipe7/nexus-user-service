package co.com.nexus.api.utilities;

import co.com.nexus.model.shared.constants.HttpStatus;
import co.com.nexus.model.shared.exception.NexusException;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public class RequestHeaderUtils {
    private static final String HEADER_USER_ID = "X-User-Id";

    private RequestHeaderUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Mono<String> getUserId(ServerRequest request) {
        return Mono.justOrEmpty(request.headers().firstHeader(HEADER_USER_ID))
                .switchIfEmpty(Mono.error(new NexusException("EL IDENTIFICADOR DE USUARIO ES REQUERIDO", HttpStatus.BAD_REQUEST)));
    }
}
