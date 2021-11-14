package ru.pel.rrs.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;
import ru.pel.rrs.entities.stays.Room;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты RoomsController. Тесты подтягивают весь контекст приложения:
 * Минусы - долгий запуск и работа. Указана последовательность запуска тестов, что создает зависимость одного теста от
 * другого.
 * Плюсы - тестируется весь путь от запроса до БД и обратно.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomsControllerRESTTest {

    private static Room room;
    private static Room updatedRoom;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void init() {
        room = new Room();
        room.setId(500);
        room.setClassOfAccommodations("suite");

        updatedRoom = new Room();
        updatedRoom.setId(500);
        updatedRoom.setClassOfAccommodations("suite+");
    }

    @Test
    @Order(10)
    void create() throws Exception {
        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(room)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 500,
                            "classOfAccommodations": "suite"
                        }
                        """));
    }

    @Test
    @Order(40)
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/rooms/{id}", room.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/rooms/{id}", room.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(30)
    void getAll() throws Exception {
        mockMvc.perform(get("/api/rooms")
                        .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(20)
    void getById() throws Exception {
        mockMvc.perform(get("/api/rooms/{id}", room.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(room)));
    }

    @Test
    @Order(21)
    void update() throws Exception {
        MvcResult result = mockMvc.perform(put("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updatedRoom)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String string = result.getResponse().getContentAsString();
        Assert.hasText("suite+", string);
    }
}