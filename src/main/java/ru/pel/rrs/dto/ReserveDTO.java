package ru.pel.rrs.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pel.rrs.entities.Guest;
import ru.pel.rrs.entities.stays.Room;
import ru.pel.rrs.entities.stays.Stays;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReserveDTO {
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "{date.must.be.in.future}")
    private LocalDateTime checkIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "{date.must.be.in.future}")
    private LocalDateTime checkOut;

    private GuestDTO guest;
    private Room room;
    private StaysDTO stays;
}
