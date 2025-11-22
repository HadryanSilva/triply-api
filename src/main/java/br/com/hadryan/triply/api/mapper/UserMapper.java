package br.com.hadryan.triply.api.mapper;

import br.com.hadryan.triply.api.entity.User;
import br.com.hadryan.triply.api.mapper.request.user.UserPostRequest;
import br.com.hadryan.triply.api.mapper.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trips", ignore = true)
    User postToUser(UserPostRequest request);

    UserResponse userToResponse(User user);

}
