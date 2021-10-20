package ru.pel.rrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pel.rrs.repositories.GuestRepository;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;


}
