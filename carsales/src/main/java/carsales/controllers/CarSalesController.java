package carsales.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Controller
public class CarSalesController {

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
