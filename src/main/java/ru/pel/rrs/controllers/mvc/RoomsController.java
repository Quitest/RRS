// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.rrs.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pel.rrs.entities.Room;
import ru.pel.rrs.services.RoomService;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/rooms")
public class RoomsController {

//    @Autowired
//    private RoomDAO roomDAO;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
//Вариант 1: реализация через @ModelAttribute. Для автоматического перевода даты и времени необходимо наличие аннотации
// на полях с датами и временем @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    //WTF @ModelAttribute в данном случае не очень-то и нужен.
    public String createRoom(@Valid @ModelAttribute("newRoom") Room room, BindingResult bindingResult) {
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
//        roomDAO.create(room);
        roomService.save(room);
        return "redirect:/rooms";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") long id) {

//        roomDAO.delete(id);
        roomService.delete(id);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(Model model, @PathVariable("id") long id) throws SQLException {
//        var room = roomDAO.getById(id);
        var room = roomService.getById(id);
        model.addAttribute("editedRoom", room);
        // 20.10.2021 логика перенесена на сервисный слой
//        if (room == null || room.getId() == 0) {
//            String msg = "Комнаты с ID=" + id + " не существует";
//            throw new NoSuchElementException(msg);
//        }
        return "/rooms/edit-room";
    }

    @GetMapping
    public String getAllRooms(Model model) {
//        model.addAttribute("roomsList", roomDAO.getAll());
        model.addAttribute("roomsList", roomService.getAll());
        return "rooms/index";
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

    @GetMapping("/{id}")
    public String roomInfo(@PathVariable("id") long id, Model model) throws SQLException {
//        var room = roomDAO.getById(id);
        var room = roomService.getById(id);
        model.addAttribute("roomInfo", room);
        //20.10.2021 логика перенесена на сервисный слой
//        if (room == null || room.getId() == 0) {
//            throw new NoSuchElementException("Комнаты с таким ID не найдено");
//        }
        return "rooms/room-info";
    }

    @PatchMapping("/{id}")
    //WTF @ModelAttribute в данном случае не очень-то и нужен.
    public String updateRoom(@Valid @ModelAttribute("editedRoom") Room room, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "/rooms/edit-room";
        }
//        roomDAO.update(room);
        roomService.save(room);
        return "redirect:/rooms";
    }
}
