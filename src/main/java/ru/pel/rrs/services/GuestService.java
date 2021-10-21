package ru.pel.rrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.pel.rrs.models.Guest;
import ru.pel.rrs.repositories.GuestRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReloadableResourceBundleMessageSource exceptionsMessageSource;

    public Guest save(Guest guest){
        return guestRepository.save(guest);
    }

    public void delete(long id){
        guestRepository.deleteById(id);
    }

    public List<Guest> getAll(){
        return guestRepository.findAll();
    }

    public Guest getById(long id){
        Guest guest = guestRepository.getById(id);
        if (guest.isEmpty()) {
            throw new NoSuchElementException(
                    exceptionsMessageSource.getMessage("room.not.found.by.id", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
        return guest;
    }
}
