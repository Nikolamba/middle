package carsales.dao;

import carsales.models.BodyType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Component
public interface DAOBodyTypeImp extends CrudRepository<BodyType, Integer>{
    boolean existsByName(String name);
}
