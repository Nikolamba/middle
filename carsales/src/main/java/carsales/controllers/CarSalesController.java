package carsales.controllers;

import carsales.LogicLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Controller
public class CarSalesController {
    private LogicLayer logic;

    @Autowired
    public CarSalesController(LogicLayer logic) {
        this.logic = logic;
    }

    @GetMapping ("/cars")
    public String showAllItems() {
        return "pages/cars.html";
    }

    @GetMapping ("/registration")
    public String showRegistrationPage() {
        return "pages/registration.html";
    }

    @GetMapping("/signin")
    public String showSigninPage() {
        return "car_sales/signin.jsp";
    }

}
