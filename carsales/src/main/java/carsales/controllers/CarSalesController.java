package carsales.controllers;

import carsales.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Controller
public class CarSalesController {

    @GetMapping ("/cars")
    public String showAllItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        return "pages/cars.html";
    }

    @GetMapping ("/registration")
    public String showRegistrationPage() {
        return "pages/registration.html";
    }

    @GetMapping("/signin")
    public String showSigninPage(@RequestParam(value = "error", required = false) String error,
                                 Model model) {
        if (error != null){
            model.addAttribute("error", "Invalid username and password!");
        }
        return "car_sales/signin.jsp";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "pages/cars.html";
    }

}
