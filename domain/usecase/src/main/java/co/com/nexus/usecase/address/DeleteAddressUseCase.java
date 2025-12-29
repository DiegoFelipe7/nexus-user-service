package co.com.nexus.usecase.address;

import co.com.nexus.model.address.gateways.AddressRepository;
import co.com.nexus.model.shared.constants.HttpStatus;
import co.com.nexus.model.shared.exception.NexusException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteAddressUseCase implements Function<UUID, Mono<Void>> {
    private final AddressRepository addressRepository;

    @Override
    public Mono<Void> apply(UUID id) {
        return addressRepository.findById(id)
                .switchIfEmpty(Mono.error(new NexusException("LA DIRECCIÓN NO EXISTE", HttpStatus.NOT_FOUND)))
                .flatMap(addressModel -> addressRepository.deleteById(addressModel.getId()));


    }
}
