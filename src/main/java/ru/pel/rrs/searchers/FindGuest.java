package ru.pel.rrs.searchers;

import ru.pel.rrs.entities.Guest;
import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.services.Service;

import java.util.List;

public class FindGuest extends Finder {

    public FindGuest(Service service) {
        super(service);
    }

    @Override
    public List<Stays> find() {
        return null;
    }
}
