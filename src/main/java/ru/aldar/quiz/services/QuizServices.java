package ru.aldar.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aldar.quiz.domain.Quiz;
import ru.aldar.quiz.repository.QuizRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuizServices {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> list() {
        return quizRepository.findAll();
    }

    public List<Quiz> listByParams(String sortName, String filterName) {
        return quizRepository.findAll();
    }

    public Quiz get(Long id) {
        return quizRepository.getOne(id);
    }

    public Quiz create(Quiz quiz) {
        return quizRepository.saveAndFlush(quiz);
    }

    public Quiz update(Quiz quiz) {
        return quizRepository.saveAndFlush(quiz);
    }

    public String delete(Long id) {
        Quiz quiz = quizRepository.getOne(id);
        if (quiz != null) {
            quizRepository.delete(quiz);
            return "Deleted";
        }
        return "Not Found!";
    }
}
