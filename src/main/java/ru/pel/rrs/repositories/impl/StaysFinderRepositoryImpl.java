package ru.pel.rrs.repositories.impl;

import ru.pel.rrs.entities.stays.Stays;
import ru.pel.rrs.repositories.StaysFinderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StaysFinderRepositoryImpl implements StaysFinderRepository {
    @PersistenceContext
    EntityManager entityManager;
//TODO ознакомиться с https://easyjava.ru/data/jpa/jpa-criteria/
    @Override
    public List<Stays> findByFacilities(Set<String> facilities) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Stays> query = cb.createQuery(Stays.class);
        Root<Stays> stays = query.from(Stays.class);

        Path<String> facilityPath = stays.get("facilities");
//        Path<String> facilityPath = stays.get("facilityName");

        List<Predicate> predicates = new ArrayList<>();
        for (String f : facilities){
            predicates.add(cb.like(facilityPath, f));
        }
        query.select(stays)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        List<Stays> resultList=null;
        try {
            TypedQuery<Stays> query1 = entityManager.createQuery(query);
            resultList = query1.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;
//        return entityManager.createQuery(query).getResultList();
    }
}
