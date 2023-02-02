package com.jepepper.sellingApp.dtos;

import com.jepepper.sellingApp.domain.UserRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClientCreationDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private List<UserRole> userRoles;
}
