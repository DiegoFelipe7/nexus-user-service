package co.com.nexus.api.address.mapper;

import co.com.nexus.api.address.dto.CreateAddressRequest;
import co.com.nexus.api.address.dto.UpdateAddressRequest;
import co.com.nexus.model.address.AddressModel;
import co.com.nexus.model.shared.pagination.QueryParams;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.UUID;

public class AddressMapper {

    private AddressMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static QueryParams mapToQueryParams(ServerRequest request) {
        return QueryParams.builder()
                .page(request.queryParam("page").map(Integer::parseInt).orElse(0))
                .size(request.queryParam("size").map(Integer::parseInt).orElse(10))
                .sortField(request.queryParam("sort").orElse("createdAt"))
                .build();
    }

    public static AddressModel mapToUpdateAddressModel(UpdateAddressRequest request) {
        return AddressModel.builder()
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .country(request.getCountry())
                .state(request.getState())
                .city(request.getCity())
                .street(request.getStreet())
                .apartment(request.getApartment())
                .postalCode(request.getPostalCode())
                .isDefault(request.isDefault())
                .build();
    }

    public static AddressModel mapToAddressModel(CreateAddressRequest request, UUID userId) {
        return AddressModel.builder()
                .fullName(request.getFullName())
                .userId(userId)
                .phone(request.getPhone())
                .country(request.getCountry())
                .state(request.getState())
                .city(request.getCity())
                .street(request.getStreet())
                .apartment(request.getApartment())
                .postalCode(request.getPostalCode())
                .isDefault(request.getIsDefault())
                .build();
    }
}
