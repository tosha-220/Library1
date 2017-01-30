package com.netcracker.controller;

import com.netcracker.model.Roles;
import com.netcracker.model.User;
import com.netcracker.service.RoleService;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @RequestMapping(value = "createUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        Roles role = new Roles(user.getLogin(), "ROLE_USER");
        try {
            userService.addUser(user);
            roleService.addUserRole(role);
        } catch (Exception e) {
            return "error";
        }
        return "redirect:/books";
    }

    @RequestMapping(value = "/createNewUser", method = RequestMethod.GET)
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "roleService")
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
