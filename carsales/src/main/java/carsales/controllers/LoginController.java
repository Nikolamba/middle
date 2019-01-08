package carsales.controllers;

import carsales.LogicLayer;
import carsales.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.security.Principal;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Controller
public class LoginController {

    private LogicLayer logic;
    private ServletContext servletContext;

    @Autowired
    public LoginController(LogicLayer logic, ServletContext servletContext) {
        this.logic = logic;
        this.servletContext = servletContext;
    }

    @GetMapping("/editcar")
    public String showEditPage(Principal principal, Model model) {
        String userName = principal.getName();
        model.addAttribute("cars", logic.findCarsByUser(logic.findUserByName(userName)));
        return "car_sales/editcar.jsp";
    }

    @PostMapping("/editcar")
    public String changeItemStatus(@RequestParam Integer carId) {
        Car car = logic.findById(carId);
        car.setStatus(!car.isStatus());
        logic.editCar(car);
        return "car_sales/editcar.jsp";
    }

    @GetMapping("/addcar")
    public String showAddCarPage(Model model) {
        model.addAttribute("brands", logic.findAllBrands());
        model.addAttribute("types", logic.findAllBodyType());
        model.addAttribute(new Car());
        return "car_sales/addcar.jsp";
    }

    @PostMapping("/addcar")
    public ModelAndView performAdditionCar(@ModelAttribute Car car,
                                     BindingResult bindingResult,
                                     @RequestParam(value = "image", required = false) MultipartFile image,
                                     ModelAndView model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.setViewName("car_sales/addcar.jsp");
        }
        if (image.isEmpty()) {
            car.setPicturePath("");
        } else {
            File fileDir = new File(servletContext.getRealPath(""));
            File filePath = new File(fileDir + File.separator + "car_resources"
                    + File.separator + image.getOriginalFilename());
            car.setPicturePath(image.getOriginalFilename());
            try {
                image.transferTo(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                model.addObject("error", "failed to save image");
                model.setViewName("car_sales/addcar.jsp");
            }
        }
        car.setSeller(logic.findUserByName(principal.getName()));
        logic.addCar(car);
        model.setViewName("pages/cars.html");
        return model;
    }
}
