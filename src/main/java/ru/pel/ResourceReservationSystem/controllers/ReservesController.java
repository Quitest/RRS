package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.GuestDAO;
import ru.pel.ResourceReservationSystem.DAO.ReserveDAO;
import ru.pel.ResourceReservationSystem.DAO.RoomDAO;
import ru.pel.ResourceReservationSystem.models.Reserve;

import javax.websocket.server.PathParam;
import java.sql.SQLException;

@Controller
@RequestMapping("/reserve")
public class ReservesController {
    @Autowired
    private ReserveDAO reserveDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private GuestDAO guestDAO;

    @GetMapping("/create")
    public String newReserve(@ModelAttribute("newReserve") Reserve newReserve, Model model){
        model.addAttribute("roomList", roomDAO.getAll());
        model.addAttribute("guestList", guestDAO.getAll());
        return "reserves/create-reserve";
    }

    @PostMapping("/create")
    public String createReserve(@ModelAttribute("newReserve") Reserve createdReserve){
        reserveDAO.create(createdReserve);
        return "redirect:/reserve";
    }

    @GetMapping
    public String reserveList(Model model){
        model.addAttribute("reserveList", reserveDAO.getAll());
        return "reserves/index";
    }

    @DeleteMapping
    public String deleteReserve(@RequestParam(value = "roomIdForDelete") int idForDelete){
        reserveDAO.delete(idForDelete);
        return "redirect:reserve/";
    }

    @GetMapping("edit/{id}")
    public String editReserve(@PathVariable("id") int reserveId, Model model) throws SQLException {
        model.addAttribute("editableReserve", reserveDAO.getById(reserveId));
        model.addAttribute("roomList", roomDAO.getAll());
        model.addAttribute("guestList", guestDAO.getAll());
        return "reserves/edit-reserve";
    }

    @PatchMapping("edit/{id}")
    public String updateReserve(@ModelAttribute("editableReserve") Reserve editedReserve){
        reserveDAO.update(editedReserve);
        return "redirect:/reserve";
    }
}
