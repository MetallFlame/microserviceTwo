package com.akriskovets.microservice.mapper;

import com.akriskovets.microservice.entity.User;
import com.akriskovets.microservice.DTO.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User toEntity(UserDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    public UserDTO toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDTO.class);
    }
}
