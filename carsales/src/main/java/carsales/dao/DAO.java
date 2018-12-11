package carsales.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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
public interface DAO<T> {

    void add(T obj);
    void edit(T obj);
    void delete(T obj);
    T findById(int id);
    List<T> findAll();
}
