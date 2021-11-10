package ru.pel.rrs.repositories.impl;

import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.entities.stays.features.Facility;
import ru.pel.rrs.repositories.StaysFinderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StaysFinderRepositoryImpl implements StaysFinderRepository {
    @PersistenceContext
    EntityManager entityManager;
//TODO ознакомиться с https://easyjava.ru/data/jpa/jpa-criteria/
    //Знакомство с Criteria API
    @Override
    public List<Stays> findByFacilities(Set<String> facilities) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Facility> facilityCriteriaQuery = cb.createQuery(Facility.class);
        Root<Facility> facilityRoot = facilityCriteriaQuery.from(Facility.class);

        facilityCriteriaQuery.select(facilityRoot).where(facilityRoot.get("facilityName").in(facilities));
        List<Facility> resultList1 = entityManager.createQuery(facilityCriteriaQuery).getResultList();


        CriteriaQuery<Stays> query = cb.createQuery(Stays.class);
        Root<Stays> stays = query.from(Stays.class);

        //источник https://www.baeldung.com/jpa-criteria-api-in-expressions
        query.select(stays).where(cb.in(stays.get("facilities")).value(facilityCriteriaQuery));

//        Path<Set<Facility>> facilityPath = stays.get("facilities");

//        Path<String> facilityName = facilityPath.get("facilityName");
//        List<Predicate> predicates = new ArrayList<>();
//        for(String f : facilities){
//            predicates.add(cb.like(f,stays.get("facilities")));
//        }


//        query.select(stays).where(cb.or(predicates.toArray(new Predicate[0])));
//        query.select(stays).where(cb.like(facilityPath,facilities));
//        query.select(stays).where(cb.gt(stays.get("roomNumber"),1));
//        query.select(stays).where(cb.like(stays.get(Stays_.roomNumber)));
//        query.select(stays).where(stays.get("facilities").in(facilities));

        List<Stays> resultList=null;
        try {
            resultList = entityManager.createQuery(query).getResultList();
//            resultList = query1.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;
//        return entityManager.createQuery(query).getResultList();
    }
}
