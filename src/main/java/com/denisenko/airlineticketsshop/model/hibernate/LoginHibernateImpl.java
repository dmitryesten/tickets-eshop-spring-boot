package com.denisenko.airlineticketsshop.model.hibernate;

import com.denisenko.airlineticketsshop.model.entity.Login;
import com.denisenko.airlineticketsshop.model.entity.User;
import com.denisenko.airlineticketsshop.model.jdbc.ILoginDao;
import com.denisenko.airlineticketsshop.service.exception.InvalidObjectFactoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Qualifier("loginHibernateImpl")
public class LoginHibernateImpl implements ILoginDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getDataLogin(Login login) throws SQLException, InvalidObjectFactoryException {
        return null;
    }

    @Override
    public boolean checkExistLogin(Login login) {
        Session session = sessionFactory.openSession();
        return Optional.ofNullable(session.get(Login.class, login.getId())).isPresent();
    }

    @Override
    public Login create(Login login) {
        Session session = sessionFactory.openSession();
        session.save(login);
        return login;
    }

    public List<Login> getLogin(){
        Session session = sessionFactory.openSession();
        return session.createQuery("from Login", Login.class).getResultList();
    }

    public Login get(Long id){
        Session session = sessionFactory.openSession();
        return session.get(Login.class, id);
    }

}
