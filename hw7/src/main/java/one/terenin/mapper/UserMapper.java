package one.terenin.mapper;

import one.terenin.dto.UserRequest;
import one.terenin.dto.UserResponse;
import one.terenin.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity map(UserRequest request);
    @InheritInverseConfiguration
    UserResponse map(UserEntity entity);

}
