package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.GuestDAO;
import ru.pel.ResourceReservationSystem.models.Guest;

@Controller
@RequestMapping("/guests")
public class GuestsController {
    @Autowired
    GuestDAO guestDAO;

    @GetMapping("/create")
    public String newGuest(@ModelAttribute("newGuest") Guest guest) {
        return "guests/create-guest";
    }

    @GetMapping
    public String getAllGuests(Model model) {
        model.addAttribute("guestsList", guestDAO.getAll());
        return "guests/index";
    }

    @PostMapping("/create")
    public String createGuest(Guest guest){
        guestDAO.create(guest);
        return "redirect:/guests";
    }

    @DeleteMapping
    public String deleteGuest(@RequestParam("guestIdForDelete") int id){
        guestDAO.delete(id);
        return "redirect:/guests";
    }
}
