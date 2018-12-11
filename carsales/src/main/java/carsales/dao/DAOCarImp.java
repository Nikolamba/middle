package carsales.dao;

import carsales.models.Car;
import carsales.models.User;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Repository
public class DAOCarImp implements DAO<Car> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.openSession().save(car);
    }

    @Override
    public void edit(Car car) {
        sessionFactory.openSession().update(car);
    }

    @Override
    public void delete(Car car) {
        sessionFactory.openSession().remove(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Car findById(int id) {
        return (Car) sessionFactory.openSession().createQuery("from Car where id = :id")
                .setParameter("id", id).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> findAll() {
        return sessionFactory.openSession().createQuery("from Car").list();
    }

    @SuppressWarnings("unchecked")
    public List<Car> findByUser(User user) {
        return sessionFactory.openSession().createQuery("from carsales.models.Car where seller = :user")
                .setParameter("user", user).list();
    }

    @SuppressWarnings("unchecked")
    public List<Car> enableFilters(Map<String, Integer> map) {
        Session session = sessionFactory.openSession();
        for (String filter : map.keySet()) {
            if (filter.equals("brandFilter")) {
                Filter fil = session.enableFilter(filter);
                fil.setParameter("brand_id", map.get(filter));
            } else {
                session.enableFilter(filter);
            }
        }
        return session.createQuery("from Car").list();
    }
}
