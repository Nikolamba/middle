package carsales.dao;

import carsales.models.Brand;
import carsales.models.Model;
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
public class DAOModelImp implements DAO<Model> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Model obj) {
        sessionFactory.openSession().save(obj);
    }

    @Override
    public void edit(Model obj) {
        sessionFactory.openSession().update(obj);
    }

    @Override
    public void delete(Model obj) {
        sessionFactory.openSession().remove(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Model findById(int id) {
        return (Model) sessionFactory.openSession().createQuery("from Model where id = :id")
                .setParameter("id", id).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Model> findAll() {
        return sessionFactory.openSession().createQuery("from Model").list();
    }

    @SuppressWarnings("unchecked")
    public List<Model> findByBrand(Brand brand) {
        return sessionFactory.openSession().createQuery("from Model where brand = :brand")
                .setParameter("brand", brand).list();
    }
}
