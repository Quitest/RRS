package ru.pel.rrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.DAO.GuestDAO;
import ru.pel.rrs.models.Guest;

import java.sql.SQLException;

@Controller
@RequestMapping("/guests")
public class GuestsController {
    @Autowired
    GuestDAO guestDAO;

    @PostMapping("/create")
    public String createGuest(Guest guest) {
        guestDAO.create(guest);
        return "redirect:/guests";
    }

    @DeleteMapping
    public String deleteGuest(@RequestParam("guestIdForDelete") int id) {
        guestDAO.delete(id);
        return "redirect:/guests";
    }

    @GetMapping("/edit/{id}")
    public String editGuest(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("editableGuest", guestDAO.getById(id));
        return "/guests/edit-guest";
    }

    @GetMapping
    public String getAllGuests(Model model) {
        model.addAttribute("guestsList", guestDAO.getAll());
        return "guests/index";
    }

    @GetMapping("/create")
    public String newGuest(@ModelAttribute("newGuest") Guest guest) {
        return "guests/create-guest";
    }

    @PatchMapping("/edit/{id}")
    public String updateGuest(Guest guest) throws SQLException {
        guestDAO.update(guest);
        return "redirect:/guests";
    }
}
