package co.com.nexus.model.user.gateways;

import co.com.nexus.model.shared.pagination.PagingResult;
import co.com.nexus.model.shared.pagination.QueryParams;
import co.com.nexus.model.user.UserModel;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {
    Mono<UserModel> saveUser(UserModel userModel);
    Mono<UserModel> findById(UUID id);
    Mono<PagingResult<UserModel>> findAllUser(QueryParams queryParams);
}
