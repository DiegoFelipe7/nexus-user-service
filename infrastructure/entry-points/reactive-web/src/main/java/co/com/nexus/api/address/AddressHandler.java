package co.com.nexus.api.address;

import co.com.nexus.api.address.dto.CreateAddressRequest;
import co.com.nexus.api.address.dto.UpdateAddressRequest;
import co.com.nexus.api.address.mapper.AddressMapper;
import co.com.nexus.api.config.ObjectValidator;
import co.com.nexus.api.utilities.RequestHeaderUtils;
import co.com.nexus.model.address.AddressModel;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.usecase.address.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddressHandler {
    private final GetAddressByIdUseCase getAddressByIdUseCase;
    private final CreateAddressUseCase createAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;
    private final GetAddressesByUserIdUseCase getAddressesByUserIdUseCase;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> getAddressById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAddressByIdUseCase.apply(UUID.fromString(id)), AddressModel.class);
    }

    public Mono<ServerResponse> getAddressesByUserId(ServerRequest request) {
        return RequestHeaderUtils.getUserId(request)
                .flatMap(userId -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAddressesByUserIdUseCase.apply(UUID.fromString(userId)), AddressModel.class));
    }

    public Mono<ServerResponse> createAddress(ServerRequest request) {
        return Mono.zip(RequestHeaderUtils.getUserId(request),
                        request.bodyToMono(CreateAddressRequest.class).doOnNext(objectValidator::validate))
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createAddressUseCase.apply(AddressMapper.mapToAddressModel(ele.getT2(), UUID.fromString(ele.getT1()))), AddressModel.class));


    }

    public Mono<ServerResponse> updateAddress(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(UpdateAddressRequest.class)
                .flatMap(objectValidator::validate)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateAddressUseCase.apply(UUID.fromString(id), AddressMapper.mapToUpdateAddressModel(ele)), AddressModel.class));
    }

    public Mono<ServerResponse> deleteAddress(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.noContent()
                .build(deleteAddressUseCase.apply(UUID.fromString(id)));
    }
}
