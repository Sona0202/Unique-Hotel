package com.dailycodework.uniquehotel.service;

import com.dailycodework.uniquehotel.exception.RoleAlreadyExistException;
import com.dailycodework.uniquehotel.exception.UserAlreadyExistsException;
import com.dailycodework.uniquehotel.model.Role;
import com.dailycodework.uniquehotel.model.Users;
import com.dailycodework.uniquehotel.repository.RoleRepository;
import com.dailycodework.uniquehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role theRole) {
        String roleName = "ROLE_"+theRole.getName().toUpperCase();
        Role role = new Role(roleName);
        if (roleRepository.existsByName(roleName)){
            throw new RoleAlreadyExistException(theRole.getName()+" role already exists");
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUsersFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public Users removeUserFromRole(Long userId, Long roleId) {
        Optional<Users> users = userRepository.findById(userId);
        Optional<Role>  role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(users.get())){
            role.get().removeUserFromRole(users.get());
            roleRepository.save(role.get());
            return users.get();
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public Users assignRoleToUser(Long user_Id, Long roleId) {
        Optional<Users> users = userRepository.findById(user_Id);
        Optional<Role>  role = roleRepository.findById(roleId);
        if (users.isPresent() && users.get().getRoles().contains(role.get())){
            throw new UserAlreadyExistsException(
                    users.get().getFirstName()+ " is already assigned to the" + role.get().getName()+ " role");
        }
        if (role.isPresent()){
            role.get().assignRoleToUser(users.get());
            roleRepository.save(role.get());
        }
        return users.get();
    }

    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        role.ifPresent(Role::removeAllUsersFromRole);
        return roleRepository.save(role.get());
    }
}
