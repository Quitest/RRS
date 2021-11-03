package ru.pel.rrs.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Веселые занятия, досуг
 */
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FunThingToDoDTO {
    private String funThing;

    /**
     * Удаленность в километрах
     */
    private float remoteness;
}
