package com.github.GaskaPiotr.spring_boot_boilerplate.mapper;

import com.github.GaskaPiotr.spring_boot_boilerplate.dto.UserResponse;
import com.github.GaskaPiotr.spring_boot_boilerplate.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserResponse userToUserResponse(User user);
}
