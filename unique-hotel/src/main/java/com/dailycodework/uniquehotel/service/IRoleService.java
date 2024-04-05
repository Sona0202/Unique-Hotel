package com.dailycodework.uniquehotel.service;


import com.dailycodework.uniquehotel.model.Role;
import com.dailycodework.uniquehotel.model.Users;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);
    Role findByName(String name);

    Users removeUserFromRole(Long userId, Long roleId);
    Users assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}
