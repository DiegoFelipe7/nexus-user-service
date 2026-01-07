package co.com.nexus.api.filters;

import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.constants.ApiConstants;
import co.com.nexus.model.shared.exception.NexusException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Component
public class AuthHeaderFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {


    @Override
    @NonNull
    public Mono<ServerResponse> filter(@NonNull ServerRequest request, @NonNull HandlerFunction<ServerResponse> next) {
        var userId = request.headers().firstHeader(ApiConstants.USER_ID_HEADER);
        if (userId.isBlank()) {
            return Mono.error(new NexusException("userId header is required", HttpStatusConstants.BAD_REQUEST));
        }
        return next.handle(request);
    }
}
