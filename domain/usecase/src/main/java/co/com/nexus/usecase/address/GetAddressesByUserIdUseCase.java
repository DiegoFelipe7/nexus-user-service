package co.com.nexus.usecase.address;

import co.com.nexus.model.address.AddressModel;
import co.com.nexus.model.address.gateways.AddressRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAddressesByUserIdUseCase implements Function<UUID, Flux<AddressModel>> {
    private final AddressRepository addressRepository;

    @Override
    public Flux<AddressModel> apply(UUID userId) {
        return addressRepository.findByUserId(userId);
    }
}
