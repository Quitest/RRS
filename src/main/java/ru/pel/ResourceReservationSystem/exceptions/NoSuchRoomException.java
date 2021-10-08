package ru.pel.ResourceReservationSystem.exceptions;

import java.util.NoSuchElementException;

public class NoSuchRoomException extends NoSuchElementException {
    public NoSuchRoomException() {
        super();
    }

    public NoSuchRoomException(String s, Throwable cause) {
        super(s, cause);
    }

    public NoSuchRoomException(Throwable cause) {
        super(cause);
    }

    public NoSuchRoomException(String s) {
        super(s);
    }
}
