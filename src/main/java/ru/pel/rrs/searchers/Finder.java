package ru.pel.rrs.searchers;

import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.services.Service;

import java.util.List;

public abstract class Finder {
    protected Service service;
     protected

    Finder(Service service) {
        this.service = service;
    }

    public abstract List<Stays> find();
}