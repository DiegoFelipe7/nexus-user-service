package co.com.nexus.r2dbc.address;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface AddressReactiveRepository extends ReactiveCrudRepository<Address, UUID>,
        ReactiveQueryByExampleExecutor<Address>,
        ReactiveSortingRepository<Address, UUID> {
    Flux<Address> findByUserId(UUID userId);
}
