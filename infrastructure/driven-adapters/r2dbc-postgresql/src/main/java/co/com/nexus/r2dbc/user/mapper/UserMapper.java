package co.com.nexus.r2dbc.user.mapper;

import co.com.nexus.model.user.UserModel;
import co.com.nexus.r2dbc.user.User;
public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static UserModel mapToModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .searchKey(user.getSearchKey())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .build();


    }

    public static User mapToEntity(UserModel userModel) {
        return User.builder()
                .userId(userModel.getUserId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .searchKey(userModel.getSearchKey())
                .phoneNumber(userModel.getPhoneNumber())
                .updatedAt(userModel.getUpdatedAt())
                .createdAt(userModel.getCreatedAt())
                .build();
    }


}
