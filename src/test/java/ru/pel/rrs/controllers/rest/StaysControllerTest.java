package ru.pel.rrs.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StaysControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeAll
    static void init() throws Exception {


    }

    @Test
    void createJSON() throws Exception {
        mockMvc.perform(post("/api/stays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "facilities": [
                                        {
                                            "facilityName": "парковка",
                                            "available": true
                                        }
                                    ],
                                    "funThingsToDo": null,
                                    "meals": [
                                        {
                                            "mealName": "завтрак",
                                            "available": true
                                        },
                                        {
                                            "mealName": "ужин",
                                            "available": true
                                        }
                                    ],
                                    "propertyType": "ROOM",
                                    "reserveSet": null,
                                    "roomFacilitiesSet": [
                                        {
                                            "roomFacilityName": "WI-FI",
                                            "available": true
                                        },
                                        {
                                            "roomFacilityName": "TV",
                                            "available": true
                                        }
                                    ],
                                    "roomNumber": 2,
                                    "number": 507
                                }"""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "facilities": [
                                {
                                    "facilityName": "парковка",
                                    "available": true
                                }
                            ],
                            "meals": [
                                {
                                    "mealName": "завтрак",
                                    "available": true
                                },
                                {
                                    "mealName": "ужин",
                                    "available": true
                                }
                            ],
                            "propertyType": "ROOM",
                            "roomFacilitiesSet": [
                                {
                                    "roomFacilityName": "TV",
                                    "available": true
                                },
                                {
                                    "roomFacilityName": "WI-FI",
                                    "available": true
                                }
                            ],
                            "roomNumber": 2,
                            "number": 507
                        }"""));
    }

    @Test
    void findStays() throws Exception {
        mockMvc.perform(post("/api/stays")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "facilities": [
                                {
                                    "facilityName": "гараж",
                                    "available": true
                                }
                            ],
                            "funThingsToDo": null,
                            "meals": [
                                {
                                    "mealName": "завтрак",
                                    "available": true
                                },
                                {
                                    "mealName": "ужин",
                                    "available": true
                                }
                            ],
                            "propertyType": "HOUSE",
                            "reserveSet": null,
                            "roomFacilitiesSet": [
                                {
                                    "roomFacilityName": "WI-FI",
                                    "available": true
                                },
                                {
                                    "roomFacilityName": "TV",
                                    "available": true
                                }
                            ],
                            "roomNumber": 2,
                            "number": 507
                        }"""));

        mockMvc.perform(get("/api/stays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"парковка\"]"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                         "facilities": [
                                        {
                                        "facilityName": "гараж",
                                        "available": true
                                        }
                         ]
                        }"""));

    }
}