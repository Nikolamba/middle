package carsales;

import carsales.dao.*;
import carsales.models.*;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Service
public class LogicLayer {
    @Autowired
    DAOCarImp daoCar;
    @Autowired
    DAOModelImp daoModel;
    @Autowired
    DAOBodyTypeImp daoBodyType;
    @Autowired
    DAOBrandImp daoBrand;
    @Autowired
    DAOUserImp daoUser;

    @PersistenceContext
    private EntityManager entityManager;

    public boolean addCar(HttpServletRequest req, Map<String, String> fields) {
        if (fields.get("year") == null || fields.get("color") == null
                || fields.get("models") == null || fields.get("body_type") == null
                || req.getSession().getAttribute("user") == null) {
            req.setAttribute("error", "adding error");
            return false;
        } else {
            Car car = new Car(Integer.valueOf(fields.get("year")),
                    fields.get("color"),
                    daoModel.findById(Integer.valueOf(fields.get("models"))).orElse(null),
                    daoBodyType.findById(Integer.valueOf(fields.get("body_type"))).orElse(null),
                    (User) req.getSession().getAttribute("user")
            );
            car.setPicturePath(fields.get("picturePath"));
            daoCar.save(car);
            return true;
        }
    }

    public User findUserByName(String name) {
        return daoUser.findByName(name);
    }

    public Iterable<Brand> findAllBrands() {
        return daoBrand.findAll();
    }

    public Iterable<BodyType> findAllBodyType() {
        return daoBodyType.findAll();
    }

    public Iterable<Car> findAllCars() {
        return daoCar.findAll();
    }

    public List<Model> findModelsByBrand(Brand brand) {
        return daoModel.findByBrand(brand);
    }

    public List<Car> findCarsByUser(User user) {
        return daoCar.findBySeller(user);
    }

    public String registrationUser(String name, String pass) {
        Iterator<User> source = daoUser.findAll().iterator();
        List<User> target = new ArrayList<>();
        source.forEachRemaining(target::add);
        if (target.stream().anyMatch(user -> user.getName().trim().equals(name))) {
            return "user with this name is already registered";
        } else {
            daoUser.save(new User(name, pass));
            return "registration successfully completed";
        }
    }

    @Transactional
    public Iterable<Car> useFilters(Map<String, Integer> filters) {
        for (String filter : filters.keySet()) {
            if (filter.equals("brandFilter")) {
                Filter fil = entityManager.unwrap(Session.class).enableFilter(filter);
                fil.setParameter("brand_id", filters.get(filter));
            } else {
                entityManager.unwrap(Session.class).enableFilter(filter);
            }
        }
        return daoCar.findAll();
    }

    public void addUser(User obj) {
        daoUser.save(obj);
    }

    public boolean isUserCredentional(String name, String pass) {
        return daoUser.existsByNameAndPass(name, pass);
    }

    public Car findById(Integer carId) {
        return daoCar.findById(carId).orElse(null);
    }

    public void editCar(Car car) {
        daoCar.save(car);
    }
}
