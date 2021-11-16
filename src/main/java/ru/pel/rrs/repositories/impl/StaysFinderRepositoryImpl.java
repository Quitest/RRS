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
    public List<Stays> findByFacilities(Set<String> facilitiesNames) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Facility> facilityCriteriaQuery = cb.createQuery(Facility.class);
        Root<Facility> facilityRoot = facilityCriteriaQuery.from(Facility.class);

        facilityCriteriaQuery.select(facilityRoot).where(facilityRoot.get("facilityName").in(facilitiesNames));
        List<Facility> facilityList = entityManager.createQuery(facilityCriteriaQuery).getResultList();


        CriteriaQuery<Stays> query = cb.createQuery(Stays.class);
        Root<Stays> staysRoot = query.from(Stays.class);

        //источник https://www.baeldung.com/jpa-criteria-api-in-expressions
        query.select(staysRoot).where(cb.in(staysRoot.get("facilities").get("facilityName")).value(facilityCriteriaQuery));

//        Path<Set<Facility>> facilityPath = staysRoot.get("facilitiesNames");

//        Path<String> facilityName = facilityPath.get("facilityName");
//        List<Predicate> predicates = new ArrayList<>();
//        for(String f : facilitiesNames){
//            predicates.add(cb.like(f,staysRoot.get("facilitiesNames")));
//        }


//        query.select(staysRoot).where(cb.or(predicates.toArray(new Predicate[0])));
//        query.select(staysRoot).where(cb.like(facilityPath,facilitiesNames));
//        query.select(staysRoot).where(cb.gt(staysRoot.get("roomNumber"),1));
//        query.select(staysRoot).where(cb.like(staysRoot.get(Stays_.roomNumber)));
//        query.select(staysRoot).where(staysRoot.get("facilitiesNames").in(facilitiesNames));

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
