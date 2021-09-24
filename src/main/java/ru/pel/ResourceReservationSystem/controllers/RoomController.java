// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.RoomDAO;
import ru.pel.ResourceReservationSystem.models.Room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomDAO roomDAO;

//Вариант 1: реализация через @ModelAttribute. Не прокатывает - преобразование строки даты и времени в объект типа LocalDateTime
// идет с ошибкой. Возможно, как-то решить можно?
//    public String createRoom(@ModelAttribute("newRoom") Room reserve){
//Вариант 2: реализация через @RequestParameter получается, но необходимо явно распарсить строку с датой и временем в
// объект LocalDateTime
    @PostMapping
    public String createRoom(@RequestParam("title") String title, @RequestParam("eventDateTime") String dateTime) {
        Room room = new Room();
        room.setTitle(title);
        room.setEventDateTime(LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME));
        roomDAO.save(room);
        return "redirect:/rooms";
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

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") int id){

        roomDAO.delete(id);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(Model model, @PathVariable("id") int id){
        model.addAttribute("room", roomDAO.getRoomById(id));
        return "/rooms/edit-room";
    }

    @PatchMapping("update/{id}")
    public String updateRoom(@ModelAttribute("room") Room editedRoom, @PathVariable("id") int id){
        roomDAO.update(id, editedRoom);
        return "redirect:/rooms";
    }
}
