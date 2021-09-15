package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.pel.ResourceReservationSystem.DAO.ResourceDAO;

@Controller
@RequestMapping("/resources")
//@ComponentScan("ru.pel.ResourceReservationSystem")
//@ResponseBody
public class ResourceController {

    @Autowired
    private ResourceDAO resourceDAO;

    @GetMapping
    public String getAllResources(Model model) {
        model.addAttribute("resourcesList", resourceDAO.getAllResources());
        return "resources/index";
    }

    @GetMapping("/{id}")
    public String getResourceById(@PathVariable("id") int id, Model model) {
        model.addAttribute("resourceInfo", resourceDAO.getResourceById(id));
        return "resources/resource-info";
    }
}
