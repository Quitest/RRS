// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.RoomDAO;
import ru.pel.ResourceReservationSystem.models.Room;

import javax.validation.Valid;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomDAO roomDAO;

    @PostMapping
//Вариант 1: реализация через @ModelAttribute. Для автоматического перевода даты и времени необходимо наличие аннотации
// на полях с датами и временем @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public String createRoom(@ModelAttribute("newRoom") @Valid Room room, BindingResult bindingResult) {
//Вариант 2: реализация через @RequestParameter получается, но необходимо явно распарсить строку с датой и временем в
// объект LocalDateTime
//    public String createRoom(@RequestParam("classOfAccommodations") String classOfAccommodations,
//                             @RequestParam("checkIn") String checkIn,
//                             @RequestParam("id") int id) {
//        Room room = new Room();
//        room.setClassOfAccommodations(classOfAccommodations);
//        room.setCheckIn(LocalDateTime.parse(checkIn, DateTimeFormatter.ISO_DATE_TIME));
//        room.setId(id);
        if (bindingResult.hasErrors()) {
            return "rooms/new-room";
        }
        roomDAO.save(room);
        return "redirect:/rooms";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") int id) {

        roomDAO.delete(id);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(Model model, @PathVariable("id") int id) {
        model.addAttribute("room", roomDAO.getRoomById(id));
        return "/rooms/edit-room";
    }

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("roomsList", roomDAO.getAllRooms());
        return "rooms/index";
    }

    @GetMapping("/{id}")
    public String getRoomById(@PathVariable("id") int id, Model model) {
        model.addAttribute("roomInfo", roomDAO.getRoomById(id));
        return "rooms/room-info";
    }

    //Вариант 1: реализации через внедрение модели.
//    @GetMapping("/new-reserve")
//    public String newRoom(Model model){
//        model.addAttribute("newRoom", new Room());
//        return "reserves/new-reserve";
//    }
//Вариант 2: реализация через аннотацию
    @GetMapping("/new-room")
    public String newRoom(@ModelAttribute("newRoom") Room newRoom) {
        //Аннотация @ModelAttribute создает объект типа Room при помощи конструктора без параметров (его наличие
        // обязательно). Конструктор без параметров присваивает полям значения по-умолчанию.
        // Далее аннотация передает новый объект в модель.

        return "rooms/new-room";
    }

    // FIXME: 26.09.2021 Разобраться с "Skipping URI variable 'id' because request contains bind value with same name"
    @PatchMapping("update/{id}")
    public String updateRoom(@ModelAttribute("room") @Valid Room editedRoom, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/rooms/edit-room";
        }
        roomDAO.update(id, editedRoom);
        return "redirect:/rooms/" + id;
    }
}
