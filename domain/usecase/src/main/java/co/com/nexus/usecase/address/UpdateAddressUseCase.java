package co.com.nexus.usecase.address;

import co.com.nexus.model.address.AddressModel;
import co.com.nexus.model.address.gateways.AddressRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateAddressUseCase implements BiFunction<UUID, AddressModel, Mono<AddressModel>> {
    private final AddressRepository addressRepository;

    @Override
    public Mono<AddressModel> apply(UUID id, AddressModel addressModel) {
        return addressRepository.findById(id)
                .map(existingAddress -> addressModel.toBuilder()
                        .id(id)
                        .userId(existingAddress.getUserId())
                        .createdAt(existingAddress.getCreatedAt())
                        .build())
                .flatMap(addressRepository::saveAddress);
    }
}
