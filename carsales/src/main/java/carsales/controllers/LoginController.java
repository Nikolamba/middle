package carsales.controllers;

import carsales.LogicLayer;
import carsales.models.Car;
import carsales.models.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@Controller
@SessionAttributes("user")
public class LoginController {
    private LogicLayer logic;

    @Autowired
    public LoginController(LogicLayer logic) {
        this.logic = logic;
    }

    @ModelAttribute("user")
    public User setUpUser() {
        return new User();
    }

    @PostMapping ("/signin")
    public String signinUser(@ModelAttribute("user") User user, Model model) {
        if (logic.isUserCredentional(user.getName(), user.getPass())) {
            int userId = logic.findUserByName(user.getName()).getId();
            user.setId(userId);
            return "pages/cars.html";
        } else {
            model.addAttribute("error", "Credentional invalid");
            return "car_sales/signin.jsp";
        }
    }

    @GetMapping("/editcar")
    public String showEditPage(@ModelAttribute("user") User user, Model model) {
        if (logic.isUserCredentional(user.getName(), user.getPass())) {
            model.addAttribute("cars", logic.findCarsByUser(user));
            return "car_sales/editcar.jsp";
        } else {
            model.addAttribute("error", "Credentional invalid");
            return "car_sales/editcar.jsp";
        }
    }

    @PostMapping("/editcar")
    public String changeItemStatus(@RequestParam Integer carId) {
        Car car = logic.findById(carId);
        car.setStatus(!car.isStatus());
        logic.editCar(car);
        return "car_sales/editcar.jsp";
    }

    @GetMapping("/addcar")
    public String showAddCarPage(@ModelAttribute("user") User user, Model model) {
        if (logic.isUserCredentional(user.getName(), user.getPass())) {
            model.addAttribute("brands", logic.findAllBrands());
            model.addAttribute("types", logic.findAllBodyType());
            model.addAttribute("user", user);
            return "car_sales/addcar.jsp";
        } else {
            model.addAttribute("error", "Credentional invalid");
            return "car_sales/addcar.jsp";
        }
    }

    @PostMapping("/addcar")
    public String performAdditionCar(@ModelAttribute("user") User user, HttpServletRequest req) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = req.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(factory);

        Map<String, String> fields = new HashMap<>();
        fields.put("year", null); fields.put("color", null);
        fields.put("models", null); fields.put("body_type", null);
        fields.put("picturePath", null);
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        if (fields.containsKey(fieldName)) {
                            fields.put(fieldName, item.getString());
                        }
                    } else {
                        File fileDir = new File(req.getServletContext().getRealPath(""));
                        if (!fileDir.exists()) {
                            fileDir.mkdirs();
                        }
                        File filePath = new File(fileDir + File.separator + "car_resources" + File.separator + item.getName());
                        System.out.println(filePath);
                        item.write(filePath);
                        fields.put("picturePath", "http://localhost:8080/carsales/car_resources/" + item.getName());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (logic.isUserCredentional(user.getName(), user.getPass()) && logic.addCar(req, fields)) {
            return "redirect:cars";
        } else {
            return "/car_sales/addcar.jsp";
        }
    }
}
