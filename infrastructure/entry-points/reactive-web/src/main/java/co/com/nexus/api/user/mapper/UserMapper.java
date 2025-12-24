package co.com.nexus.api.user.mapper;

import co.com.nexus.api.user.dto.UpdateUserRequest;
import co.com.nexus.model.shared.pagination.QueryParams;
import co.com.nexus.model.user.UserModel;
import org.springframework.web.reactive.function.server.ServerRequest;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static QueryParams mapToQueryParams(ServerRequest request) {
        return QueryParams.builder()
                .page(request.queryParam("page").map(Integer::parseInt).orElse(0))
                .size(request.queryParam("size").map(Integer::parseInt).orElse(10))
                .sortField(request.queryParam("sort").orElse("createdAt"))
                .build();

    }

    public static UserModel mapToUserModel(UpdateUserRequest request) {
        return UserModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
}
