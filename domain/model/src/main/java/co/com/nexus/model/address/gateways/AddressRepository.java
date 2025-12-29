package co.com.nexus.model.address.gateways;

import co.com.nexus.model.address.AddressModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AddressRepository {
    Mono<AddressModel> saveAddress(AddressModel addressModel);
    Mono<AddressModel> findById(UUID id);
    Flux<AddressModel> findByUserId(UUID userId);
    Mono<Void> deleteById(UUID id);
}
