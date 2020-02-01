package ru.aldar.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aldar.quiz.domain.Quiz;
import ru.aldar.quiz.utils.Utils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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

        return em.createQuery(query.select(from).where(builder.and(predicates.toArray(new Predicate[]{})))).setFirstResult(size == 1 ? 0 : (page - 1) * size).setMaxResults(size).getResultList();
    }
}
