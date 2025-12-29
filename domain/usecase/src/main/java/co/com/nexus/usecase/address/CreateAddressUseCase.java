package co.com.nexus.usecase.address;

import co.com.nexus.model.address.AddressModel;
import co.com.nexus.model.address.gateways.AddressRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateAddressUseCase implements Function<AddressModel, Mono<AddressModel>> {

    private final AddressRepository addressRepository;

    @Override
    public Mono<AddressModel> apply(AddressModel addressModel) {
        return addressRepository.saveAddress(addressModel);
    }
}
