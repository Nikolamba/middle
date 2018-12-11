package carsales.dao;

import carsales.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Component
public class DAOUserImp implements DAO<User> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(User obj) {
        sessionFactory.openSession().save(obj);
    }

    @Override
    public void edit(User obj) {
        sessionFactory.openSession().update(obj);
    }

    @Override
    public void delete(User obj) {
        sessionFactory.openSession().remove(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findById(int id) {
        return (User) sessionFactory.openSession().createQuery("from User where id = :id")
                .setParameter("id", id).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return sessionFactory.openSession().createQuery("from User").list();
    }

    @SuppressWarnings("unchecked")
    public User findByName(String name) {
        return (User) sessionFactory.openSession().createQuery("from User where name = :name")
                .setParameter("name", name).getResultList().stream().findFirst().orElse(null);
    }

    public boolean isCredentional(String name, String pass) {
        User user = findByName(name);
        return  (user != null && user.getPass().trim().equals(pass));
    }
}
