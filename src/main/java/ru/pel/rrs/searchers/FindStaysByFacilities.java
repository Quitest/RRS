package ru.pel.rrs.searchers;

import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.services.Service;

import java.util.List;

public class FindStaysByFacilities extends Finder {
    public FindStaysByFacilities(Service service) {
        super(service);
    }

    @Override
    public List<Stays> find() {
        service.findBy();
        return null;
    }
}
