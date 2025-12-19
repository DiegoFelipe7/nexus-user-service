package co.com.nexus.r2dbc.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserReactiveRepository extends ReactiveCrudRepository<User, UUID>, 
        ReactiveQueryByExampleExecutor<User>, 
        ReactiveSortingRepository<User, UUID> {
    
    Flux<User> findAllBy(Pageable pageable);

}
