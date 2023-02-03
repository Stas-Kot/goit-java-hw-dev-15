package com.goit.feature.mvc.user.dto;

import com.goit.feature.mvc.user.User;
import com.goit.feature.mvc.user.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private UserRole role;

    public static UserDto fromUser(User user) {
        UserDto res = new UserDto();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setRole(user.getRole());
        return res;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setRole(userDto.getRole());
        return user;
    }
}
