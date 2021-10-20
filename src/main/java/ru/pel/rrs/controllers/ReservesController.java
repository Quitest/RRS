package ru.pel.rrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.DAO.GuestDAO;
import ru.pel.rrs.DAO.ReserveDAO;
import ru.pel.rrs.DAO.RoomDAO;
import ru.pel.rrs.models.Reserve;
import ru.pel.rrs.services.ReserveService;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/reserves")
public class ReservesController {
//    @Autowired
//    private ReserveDAO reserveDAO;
//    @Autowired
//    private RoomDAO roomDAO;
//    @Autowired
//    private GuestDAO guestDAO;

    @Autowired
    private ReserveService reserveService;

    @PostMapping("/create")
    public String createReserve(@Valid @ModelAttribute("newReserve")  Reserve createdReserve, BindingResult bindingResult,
                                Model model) throws SQLException {
        if (bindingResult.hasErrors()){
            model.addAttribute("editableReserve", createdReserve);
//            model.addAttribute("roomList", roomDAO.getAll());
//            model.addAttribute("guestList", guestDAO.getAll());
            model.addAttribute("roomList", reserveService.getRoomsList());
            model.addAttribute("guestList", reserveService.getGuestsList());
            return "/reserves/create-reserve";
        }
//        reserveDAO.create(createdReserve);
        reserveService.save(createdReserve);
        return "redirect:/reserves";
    }

    @DeleteMapping
    public String deleteReserve(@RequestParam(value = "roomIdForDelete") Long idForDelete) {
//        reserveDAO.delete(idForDelete);
        reserveService.delete(idForDelete);
        return "redirect:reserves/";
    }

    @GetMapping("edit/{id}")
    public String editReserve(@PathVariable("id") long reserveId, Model model) throws SQLException {
//        model.addAttribute("editableReserve", reserveDAO.getById(reserveId));
//        model.addAttribute("roomList", roomDAO.getAll());
//        model.addAttribute("guestList", guestDAO.getAll());
        model.addAttribute("editableReserve", reserveService.getById(reserveId));
        model.addAttribute("roomList", reserveService.getRoomsList());
        model.addAttribute("guestList", reserveService.getGuestsList());
        return "reserves/edit-reserve";
    }

    @GetMapping("/create")
    public String newReserve(@ModelAttribute("newReserve") Reserve newReserve, Model model) {
//        model.addAttribute("roomList", roomDAO.getAll());
//        model.addAttribute("guestList", guestDAO.getAll());
        model.addAttribute("roomList", reserveService.getRoomsList());
        model.addAttribute("guestList", reserveService.getGuestsList());
        return "reserves/create-reserve";
    }

    @GetMapping
    public String reserveList(Model model) {
//        model.addAttribute("reserveList", reserveDAO.getAll());
        model.addAttribute("reserveList", reserveService.getAll());
        return "reserves/index";
    }

    @PatchMapping("edit/{id}")
    public String updateReserve(@Valid @ModelAttribute("editableReserve") Reserve editedReserve, BindingResult bindingResult, Model model) throws SQLException {
        if (bindingResult.hasErrors()){
            model.addAttribute("editableReserve", editedReserve);
//            model.addAttribute("roomList", roomDAO.getAll());
//            model.addAttribute("guestList", guestDAO.getAll());
            model.addAttribute("roomList", reserveService.getRoomsList());
            model.addAttribute("guestList", reserveService.getGuestsList());
            return "/reserves/edit-reserve";
        }
//        reserveDAO.update(editedReserve);
        reserveService.save(editedReserve);
        return "redirect:/reserves";
    }
}
