package co.com.nexus.usecase.user;

import co.com.nexus.model.user.UserModel;
import co.com.nexus.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateUserUseCase implements Function<UserModel, Mono<Void>> {

    private final UserRepository userRepository;

    @Override
    public Mono<Void> apply(UserModel userModel) {
        return userRepository.saveUser(userModel).then();
    }
}
