package com.enigma.loanapp.service;

import com.enigma.loanapp.entity.Role;
import com.enigma.loanapp.util.enums.ERole;

public interface RoleService {
    Role getOrSave(ERole role);
}