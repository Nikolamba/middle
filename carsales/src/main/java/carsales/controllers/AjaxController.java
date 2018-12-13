package carsales.controllers;

import carsales.LogicLayer;
import carsales.models.Brand;
import carsales.models.Car;
import carsales.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Controller
public class AjaxController {
    private LogicLayer logic;

    @Autowired
    public AjaxController(LogicLayer logic) {
        this.logic = logic;
    }

    @GetMapping("/filters")
    @ResponseBody
    public Iterable<Car> getAllCarsByFilters(@RequestParam Integer brandId,
                                         @RequestParam Boolean onlyFoto, @RequestParam Boolean currentData) {
        Map<String, Integer> filterMap = new HashMap<>();
        if (brandId != -1) {
            filterMap.put("brandFilter", brandId);
        }
        if (onlyFoto) {
            filterMap.put("onlyFotoFilter", 1);
        }
        if (currentData) {
            filterMap.put("currentDataFilter", 1);
        }
        return  (filterMap.isEmpty()) ? logic.findAllCars() : logic.useFilters(filterMap);
    }

    @GetMapping("/getmodels")
    @ResponseBody
    public List<Model> getModelsByBrand(@RequestParam Integer brand) {
        Brand brand1 = new Brand();
        brand1.setId(brand);
        return logic.findModelsByBrand(brand1);
    }

    @GetMapping("/allbrand")
    @ResponseBody
    public Iterable<Brand> getAllBrands() {
        return logic.findAllBrands();
    }

    @GetMapping ("/allcars")
    @ResponseBody
    public Iterable<Car> getAllCars() {
        return logic.findAllCars();
    }

    @PostMapping ("/registration")
    @ResponseBody
    public String registrationUser(@RequestParam String name, @RequestParam String pass) {
        return logic.registrationUser(name, pass);
    }
}
