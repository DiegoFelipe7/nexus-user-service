package co.com.nexus.api.user;

import co.com.nexus.api.config.ObjectValidator;
import co.com.nexus.api.user.dto.UpdateUserRequest;
import co.com.nexus.api.user.mapper.UserMapper;
import co.com.nexus.model.shared.exception.CustomException;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.usecase.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

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
                .flatMap(parts -> {
                    FilePart filePart = (FilePart) parts.toSingleValueMap().get("file");
                    if (filePart == null) {
                        return ServerResponse.badRequest().bodyValue(new CustomException("File part 'file' is missing"));
                    }
                    return filePart.content()
                            .reduce(new java.io.ByteArrayOutputStream(), (baos, dataBuffer) -> {
                                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(bytes);
                                baos.write(bytes, 0, bytes.length);
                                return baos;
                            })
                            .map(java.io.ByteArrayOutputStream::toByteArray)
                            .flatMap(bytes -> updateUserAvatarUseCase.apply(UUID.fromString(id), bytes))
                            .flatMap(url -> ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(Map.of("url", url))
                            );
                });
    }

}
