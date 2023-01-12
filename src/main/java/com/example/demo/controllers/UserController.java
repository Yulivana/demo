package com.example.demo.controllers;

import com.example.demo.fasads.UserFasad;
import com.example.demo.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserController {

    @Autowired
    UserFasad userFasad;

    @GetMapping("/users")
    public ResponseEntity<List<UserForm>> getUsers(@RequestParam(required = false, value = "id") Long id) {
        List<UserForm> users = userFasad.getAll();
        return new ResponseEntity<>(users, OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserForm> getUserById(@RequestParam(value = "id") Long id) {
        UserForm userForm = userFasad.getById(id);
        return new ResponseEntity<>(userForm, OK);
    }

    @GetMapping("/usersAll")
    public ModelAndView getUsersAll() {
        List<UserForm> users = userFasad.getAll();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView("welcome");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = false, value = "error") String error) throws IOException {
        ModelAndView model = new ModelAndView("loginPage");
        UserForm loginForm = new UserForm();
        model.addObject("loginForm", loginForm);
        if(error !=null) {
            request.getSession().setAttribute("error", "");
            response.sendRedirect(request.getContextPath()+"/login");
        }
        return model;
    }

    @RequestMapping(value = "/loginS",method = RequestMethod.GET)
    public void executeLogin(HttpServletRequest request, HttpServletResponse res) throws IOException {
        res.sendRedirect(request.getContextPath() + "/welcome");
    }

}
