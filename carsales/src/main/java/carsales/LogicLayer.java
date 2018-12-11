package carsales;

import carsales.dao.*;
import carsales.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    public boolean addCar(HttpServletRequest req, Map<String, String> fields) {
        if (fields.get("year") == null || fields.get("color") == null
                || fields.get("models") == null || fields.get("body_type") == null
                || req.getSession().getAttribute("user") == null) {
            req.setAttribute("error", "adding error");
            return false;
        } else {
            Car car = new Car(Integer.valueOf(fields.get("year")),
                    fields.get("color"),
                    daoModel.findById(Integer.valueOf(fields.get("models"))),
                    daoBodyType.findById(Integer.valueOf(fields.get("body_type"))),
                    (User) req.getSession().getAttribute("user")
            );
            car.setPicturePath(fields.get("picturePath"));
            daoCar.add(car);
            return true;
        }
    }

    public User findUserByName(String name) {
        return daoUser.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Brand> findAllBrands() {
        return daoBrand.findAll();
    }

    public List<BodyType> findAllBodyType() {
        return daoBodyType.findAll();
    }

    @Transactional(readOnly = true)
    public List<Car> findAllCars() {
        return daoCar.findAll();
    }

    public List<Model> findModelsByBrand(Brand brand) {
        return daoModel.findByBrand(brand);
    }

    @Transactional
    public List<Car> findCarsByUser(User user) {
        return daoCar.findByUser(user);
    }

    @Transactional
    public String registrationUser(String name, String pass) {
        if (daoUser.findAll().stream().anyMatch(user -> user.getName().trim().equals(name))) {
            return "user with this name is already registered";
        } else {
            daoUser.add(new User(name, pass));
            return "registration successfully completed";
        }
    }

    @Transactional(readOnly = true)
    public List<Car> useFilters(Map<String, Integer> filters) {
        return daoCar.enableFilters(filters);
    }

    @Transactional
    public void addUser(User obj) {
        daoUser.add(obj);
    }

    @Transactional(readOnly = true)
    public boolean isUserCredentional(String name, String pass) {
        return daoUser.isCredentional(name, pass);
    }

    @Transactional(readOnly = true)
    public Car findById(Integer carId) {
        return daoCar.findById(carId);
    }

    @Transactional
    public void editCar(Car car) {
        daoCar.edit(car);
    }
}
