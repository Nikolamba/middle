package carsales.dao;

import carsales.models.Brand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Repository
public class DAOBrandImp implements DAO<Brand> {

    @Autowired
    SessionFactory sessionFactory;
//    @Autowired
//    private final static String[] BRANDS_LIST = {"Audi", "BMW", "Volvo", "Mazda"};

    public DAOBrandImp() { }
//    private DAOBrandImp() {
//        for (String str : BRANDS_LIST) {
//            if (!findByName(str)) {
//                Brand brand = new Brand();
//                brand.setName(str);
//                this.add(brand);
//            }
//        }
//    }

    @Override
    public void add(Brand obj) {
        sessionFactory.openSession().save(obj);
    }

    @Override
    public void edit(Brand obj) {
        sessionFactory.openSession().update(obj);
    }

    @Override
    public void delete(Brand obj) {
        sessionFactory.openSession().remove(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Brand findById(int id) {
        return (Brand) sessionFactory.openSession().createQuery("from Brand where id = :id")
                .setParameter("id", id).getResultList().stream().findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    public boolean findByName(String name) {
        return sessionFactory.openSession().createQuery("from Brand where name = :name")
                .setParameter("name", name).getResultList().isEmpty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Brand> findAll() {
        return sessionFactory.openSession().createQuery("from Brand").list();
    }
}
