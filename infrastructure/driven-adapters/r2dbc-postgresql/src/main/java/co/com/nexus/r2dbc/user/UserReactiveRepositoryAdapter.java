package co.com.nexus.r2dbc.user;

import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.model.user.gateways.UserRepository;
import co.com.nexus.r2dbc.helper.ReactiveAdapterOperations;
import co.com.nexus.r2dbc.user.mapper.UserMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        UserModel,
        User,
        UUID,
        UserReactiveRepository
        > implements UserRepository {

    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, UserModel.UserModelBuilder.class).build());
    }

    @Override
    public Mono<UserModel> saveUser(UserModel userModel) {
        return this.repository.save(UserMapper.mapToEntity(userModel))
                .map(UserMapper::mapToModel);
    }

    @Override
    public Mono<PagingResult<UserModel>> findAllUser(QueryParams queryParams) {
        Pageable pageable = PageRequest.of(queryParams.getPage(), queryParams.getSize());
        return this.repository.findAllBy(pageable)
                .collectList()
                .zipWith(this.repository.count())
                .map(tuple -> new PagingResult<>(
                        tuple.getT1().stream().map(UserMapper::mapToModel).toList(),
                        queryParams.getPage(),
                        queryParams.getSize(),
                        tuple.getT2(),
                        (int) Math.ceil((double) tuple.getT2() / queryParams.getSize())
                ));
    }

}
