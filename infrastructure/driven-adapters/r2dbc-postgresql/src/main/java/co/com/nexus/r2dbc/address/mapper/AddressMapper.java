package co.com.nexus.r2dbc.address.mapper;

import co.com.nexus.model.address.AddressModel;
import co.com.nexus.r2dbc.address.Address;

public class AddressMapper {
    private AddressMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AddressModel mapToModel(Address address) {
        return AddressModel.builder()
                .id(address.getId())
                .userId(address.getUserId())
                .fullName(address.getFullName())
                .phone(address.getPhone())
                .country(address.getCountry())
                .state(address.getState())
                .city(address.getCity())
                .street(address.getStreet())
                .apartment(address.getApartment())
                .postalCode(address.getPostalCode())
                .isDefault(address.isDefault())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }

    public static Address mapToEntity(AddressModel addressModel) {
        return Address.builder()
                .id(addressModel.getId())
                .userId(addressModel.getUserId())
                .fullName(addressModel.getFullName())
                .phone(addressModel.getPhone())
                .country(addressModel.getCountry())
                .state(addressModel.getState())
                .city(addressModel.getCity())
                .street(addressModel.getStreet())
                .apartment(addressModel.getApartment())
                .postalCode(addressModel.getPostalCode())
                .isDefault(addressModel.isDefault())
                .createdAt(addressModel.getCreatedAt())
                .updatedAt(addressModel.getUpdatedAt())
                .build();
    }
}
