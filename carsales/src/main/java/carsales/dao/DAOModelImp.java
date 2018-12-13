package carsales.dao;

import carsales.models.Brand;
import carsales.models.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Component
public interface DAOModelImp extends CrudRepository<Model, Integer> {
    List<Model> findByBrand(Brand brand);
}
