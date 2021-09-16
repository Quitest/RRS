// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.ResourceReservationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pel.ResourceReservationSystem.DAO.ReserveDAO;
import ru.pel.ResourceReservationSystem.models.Reserve;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/reserves")
public class ReserveController {

    @Autowired
    private ReserveDAO reserveDAO;

//Вариант 1: реализация через @ModelAttribute. Не прокатывает - преобразование строки даты и времени в обхект типа LocalDateTime
// идет с ошибкой. Возможно, как-то решить можно?
//    public String createReserve(@ModelAttribute("newReserve") Reserve reserve){
//Вариант 2: реализация через @RequestParameter получается, но необходимо явно распарсить строку с датой и временем в
// объект LocalDateTime
    @PostMapping
    public String createReserve(@RequestParam("title") String title, @RequestParam("eventDateTime") String dateTime) {
        Reserve reserve = new Reserve();
        reserve.setTitle(title);
        reserve.setEventDateTime(LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME));
        reserveDAO.save(reserve);
        return "redirect:/reserves";
    }

    @GetMapping
    public String getAllReserves(Model model) {
        model.addAttribute("reservesList", reserveDAO.getAllReserves());
        return "reserves/index";
    }

    @GetMapping("/{id}")
    public String getReserveById(@PathVariable("id") int id, Model model) {
        model.addAttribute("reserveInfo", reserveDAO.getReserveById(id));
        return "reserves/reserve-info";
    }

    //Вариант 1: реализации через внедрение модели.
//    @GetMapping("/new-reserve")
//    public String newReserve(Model model){
//        model.addAttribute("newReserve", new Reserve());
//        return "reserves/new-reserve";
//    }
//Вариант 2: реализация через аннотацию
    @GetMapping("/new-reserve")
    public String newReserve(@ModelAttribute("newReserve") Reserve newReserve) {
        //Аннотация @ModelAttribute создает объект типа Reserve при помощи конструктора без параметров (его наличие
        // обязательно). Конструктор без параметров присваивает полям значения по-умолчанию.
        // Далее аннотация передает новый объект в модель.
        return "reserves/new-reserve";
    }

    @DeleteMapping("delete/{id}")
    public String deleteReserve(@PathVariable("id") int id){
        reserveDAO.delete(id);
        return "redirect:/reserves";
    }
}
