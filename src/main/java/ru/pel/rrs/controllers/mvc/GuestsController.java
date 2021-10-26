package ru.pel.rrs.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.entities.Guest;
import ru.pel.rrs.repositories.GuestRepository;

import java.sql.SQLException;

@Controller
@RequestMapping("/guests")
public class GuestsController {
//    @Autowired
//    GuestDAO guestDAO;

    @Autowired
    private GuestRepository guestService;

    @PostMapping("/create")
    public String createGuest(Guest guest) {
//        guestDAO.create(guest);
        guestService.save(guest);
        return "redirect:/guests";
    }

    @DeleteMapping
    public String deleteGuest(@RequestParam("guestIdForDelete") long id) {
//        guestDAO.delete(id);
        guestService.deleteById(id);
        return "redirect:/guests";
    }

    @GetMapping("/edit/{id}")
    public String editGuest(@PathVariable("id") long id, Model model) throws SQLException {
//        model.addAttribute("editableGuest", guestDAO.getById(id));
        model.addAttribute("editableGuest", guestService.getById(id));
        return "/guests/edit-guest";
    }

    @GetMapping
    public String getAllGuests(Model model) {
//        model.addAttribute("guestsList", guestDAO.getAll());
        model.addAttribute("guestsList", guestService.findAll());
        return "guests/index";
    }

    @GetMapping("/create")
    public String newGuest(@ModelAttribute("newGuest") Guest guest) {
        return "guests/create-guest";
    }

    @PatchMapping("/edit/{id}")
    public String updateGuest(Guest guest) throws SQLException {
//        guestDAO.update(guest);
        guestService.save(guest);
        return "redirect:/guests";
    }
}
