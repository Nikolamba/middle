package carsales.dao;

import carsales.models.BodyType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Component
public class DAOBodyTypeImp implements DAO<BodyType> {
    @Autowired
    SessionFactory sessionFactory;

    //private String[] bodytype_list;

    @Autowired
    public DAOBodyTypeImp() { }

    @Override
    public void add(BodyType obj) {
        sessionFactory.openSession().save(obj);
    }

    @Override
    public void edit(BodyType obj) {
        sessionFactory.openSession().update(obj);
    }

    @Override
    public void delete(BodyType obj) {
        sessionFactory.openSession().remove(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public BodyType findById(int id) {
        return (BodyType) sessionFactory.openSession().createQuery("from BodyType where id = :id")
                .setParameter("id", id).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BodyType> findAll() {
        return sessionFactory.openSession().createQuery("from BodyType").list();
    }

    @SuppressWarnings("unchecked")
    public boolean findByName(String name) {
        return !sessionFactory.openSession().createQuery("from BodyType where name = :name")
                .setParameter("name", name).getResultList().isEmpty();
    }
}
