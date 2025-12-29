package co.com.nexus.r2dbc.address;

import co.com.nexus.model.address.AddressModel;
import co.com.nexus.model.address.gateways.AddressRepository;
import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import co.com.nexus.r2dbc.address.mapper.AddressMapper;
import co.com.nexus.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class AddressReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        AddressModel,
        Address,
        UUID,
        AddressReactiveRepository
        > implements AddressRepository {

    public AddressReactiveRepositoryAdapter(AddressReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, AddressModel.AddressModelBuilder.class).build());
    }

    @Override
    public Mono<AddressModel> saveAddress(AddressModel addressModel) {
        return this.repository.save(AddressMapper.mapToEntity(addressModel))
                .map(AddressMapper::mapToModel);
    }


    @Override
    public Flux<AddressModel> findByUserId(UUID userId) {
        return this.repository.findByUserId(userId)
                .map(AddressMapper::mapToModel);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return this.repository.deleteById(id);
    }
}
