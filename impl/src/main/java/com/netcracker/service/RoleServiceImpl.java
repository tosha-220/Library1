package com.netcracker.service;


import com.netcracker.dao.RoleDao;
import com.netcracker.model.Roles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void addUserRole(Roles role) {
        roleDao.addUserRole(role);
    }
}
