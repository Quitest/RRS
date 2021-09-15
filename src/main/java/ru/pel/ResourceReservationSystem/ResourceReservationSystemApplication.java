// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
package ru.pel.ResourceReservationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//TODO для создания сильно нагруженного приложения необходим переход на реактивное программирование -
// Spring Reactive Web. Использовать реактивные БД, клиент и сервер.
@SpringBootApplication
public class ResourceReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceReservationSystemApplication.class, args);
	}

}
