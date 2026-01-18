package co.com.nexus.api.user;

import co.com.nexus.api.config.ObjectValidator;
import co.com.nexus.api.user.dto.UpdateUserRequest;
import co.com.nexus.api.user.mapper.UserMapper;
import co.com.nexus.model.s3.FileUploadModel;
import co.com.nexus.model.shared.constants.HttpStatusConstants;
import co.com.nexus.model.shared.exception.CustomException;
import co.com.nexus.model.shared.exception.NexusException;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.usecase.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserHandler {
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UpdateUserAvatarUseCase updateUserAvatarUseCase;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllUsersUseCase.apply(UserMapper.mapToQueryParams(request)), PagingResult.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getUserByIdUseCase.apply(UUID.fromString(id)), UserModel.class);
    }

    public Mono<ServerResponse> updateUser(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(UpdateUserRequest.class)
                .flatMap(objectValidator::validate)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateUserUseCase.apply(UUID.fromString(id), UserMapper.mapToUserModel(ele)), UserModel.class));
    }

    public Mono<ServerResponse> updateUserAvatar(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.multipartData()
                .flatMap(parts -> Mono.justOrEmpty(parts.getFirst("file")).ofType(FilePart.class))
                .switchIfEmpty(Mono.error(new NexusException("No file part found", HttpStatusConstants.BAD_REQUEST)))
                .flatMap(file -> DataBufferUtils.join(file.content())
                        .map(dataBuffer -> {
                            byte[] bytes = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(bytes);
                            DataBufferUtils.release(dataBuffer);
                            return new FileUploadModel(
                                    file.filename(),
                                    file.headers().getContentType().toString(),
                                    bytes.length,
                                    bytes
                            );
                        }))
                .flatMap(ele ->
                        ServerResponse.ok()
                                .contentType(MediaType.TEXT_PLAIN)
                                .body(updateUserAvatarUseCase.apply(UUID.fromString(id), ele), String.class));


    }

}
