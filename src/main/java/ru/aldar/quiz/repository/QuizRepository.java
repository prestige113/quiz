package ru.aldar.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aldar.quiz.domain.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
