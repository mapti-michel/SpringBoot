package br.acc.webflux.mapper;

import br.acc.webflux.entity.User;
import br.acc.webflux.model.request.UserRequest;
import br.acc.webflux.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest request);

    @Mapping(target = "id", ignore = true)
    User toEntity(final UserRequest request, @MappingTarget User entity);

    UserResponse toResponse(final User entity);

}
