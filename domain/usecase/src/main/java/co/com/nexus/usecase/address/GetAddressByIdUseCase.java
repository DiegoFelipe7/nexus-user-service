package co.com.nexus.usecase.address;

import co.com.nexus.model.address.AddressModel;
import co.com.nexus.model.address.gateways.AddressRepository;
import co.com.nexus.model.shared.constants.HttpStatus;
import co.com.nexus.model.shared.exception.NexusException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAddressByIdUseCase implements Function<UUID, Mono<AddressModel>> {
    private final AddressRepository addressRepository;

    @Override
    public Mono<AddressModel> apply(UUID uuid) {
        return addressRepository.findById(uuid)
                .switchIfEmpty(Mono.error(new NexusException("DIRECCIÓN NO ENCONTRADA", HttpStatus.NOT_FOUND)));
    }
}
