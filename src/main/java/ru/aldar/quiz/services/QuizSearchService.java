package ru.aldar.quiz.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.aldar.quiz.domain.Quiz;
import ru.aldar.quiz.utils.Utils;

@Service
public class QuizSearchService {

    private final EntityManager em;

    @Autowired
    public QuizSearchService(EntityManager em) {
        this.em = em;
    }

    public List<Quiz> search(String name,
                             Long start,
                             Boolean enable,
                             Boolean sortName,
                             Integer page,
                             Integer size) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Quiz> query = builder.createQuery(Quiz.class);
        Root<Quiz> from = query.from(Quiz.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Utils.isNull(name)) {
            predicates.add(builder.like(builder.lower(from.get("name")), "%" + name + "%"));
        }

        if (Utils.isNull(start)) {
            predicates.add(builder.equal(from.get("startDate"), Utils.toDate(start)));
        }
        if (enable != null) {
            if (enable) {
                predicates.add(builder.isTrue(from.get("enable")));
            } else {
                predicates.add(builder.isFalse(from.get("enable")));
            }
        }
        if (sortName != null) {
            if (sortName) {
                query.orderBy(
                        builder.asc(from.get("name"))
                );
            } else {
                query.orderBy(
                        builder.asc(from.get("startDate"))
                );
            }
        }

        return em.createQuery(query.select(from).where(builder.and(predicates.toArray(new Predicate[]{})))).setFirstResult(page * size).setMaxResults(size).getResultList();
    }
}
