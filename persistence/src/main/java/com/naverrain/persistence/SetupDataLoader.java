package com.naverrain.persistence;

import com.naverrain.persistence.dao.PrivilegeDao;
import com.naverrain.persistence.dao.RoleDao;
import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dto.PrivilegeDto;
import com.naverrain.persistence.dto.RoleDto;
import com.naverrain.persistence.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    public static final String READ_PRIVILEGE = "READ_PRIVILEGE";
    public static final String WRITE_PRIVILEGE = "WRITE_PRIVILEGE";
    public static final String DELETE_PRIVILEGE = "DELETE_PRIVILEGE";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    private boolean isAlreadyConfigured;
    @Autowired
    private UserDao userRepository;
    @Autowired
    private RoleDao roleRepository;
    @Autowired
    private PrivilegeDao privilegeRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (isAlreadyConfigured) {
            return;
        }
        PrivilegeDto readPrivilege = createPrivilegeIfNotFound(READ_PRIVILEGE);
        PrivilegeDto writePrivilege = createPrivilegeIfNotFound(WRITE_PRIVILEGE);
        PrivilegeDto deletePrivilege = createPrivilegeIfNotFound(DELETE_PRIVILEGE);
        List<PrivilegeDto> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, deletePrivilege);
        List<PrivilegeDto> managerPrivileges = Arrays.asList(readPrivilege, writePrivilege);

        createRoleIfNotFound(ROLE_ADMIN, adminPrivileges);
        createRoleIfNotFound(ROLE_MANAGER, managerPrivileges);
        createRoleIfNotFound(ROLE_CUSTOMER, Arrays.asList(readPrivilege));
        RoleDto adminRole = roleRepository.getRoleByRoleName(ROLE_ADMIN);
        RoleDto managerRole = roleRepository.getRoleByRoleName(ROLE_MANAGER);

        createUserIfNotFound("admin@test.com", adminRole, "admin");
        createUserIfNotFound("manager@test.com", managerRole, "manager");
        isAlreadyConfigured = true;
    }
    @Transactional
    private void createUserIfNotFound(String email, RoleDto role, String password) {
        UserDto user = userRepository.getUserByEmail(email);
        if (user == null) {
            user = new UserDto();
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setRoles(Arrays.asList(role));
            user.setEnabled(true);
            userRepository.saveUser(user);
        }
    }
    @Transactional
    private PrivilegeDto createPrivilegeIfNotFound(String name) {
        PrivilegeDto privilege = privilegeRepository.getPrivilegeByName(name);
        if (privilege == null) {
            privilege = new PrivilegeDto(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
    @Transactional
    private RoleDto createRoleIfNotFound(String name, List<PrivilegeDto> privileges) {
        RoleDto role = roleRepository.getRoleByRoleName(name);
        if (role == null) {
            role = new RoleDto(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
