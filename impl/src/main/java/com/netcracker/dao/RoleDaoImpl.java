package com.netcracker.dao;

import com.netcracker.model.Roles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private SessionFactory sessionFactory;

    @Override
    public void addUserRole(Roles role) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(role);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
