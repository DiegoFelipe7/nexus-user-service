package co.com.nexus.usecase.user;

import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import co.com.nexus.model.user.UserModel;
import co.com.nexus.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllUsersUseCase implements Function<QueryParams, Mono<PagingResult<UserModel>>> {
    private final UserRepository userRepository;

    @Override
    public Mono<PagingResult<UserModel>> apply(QueryParams queryParams) {
        return userRepository.findAllUser(queryParams);
    }
}
