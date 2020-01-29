package ru.aldar.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aldar.quiz.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
