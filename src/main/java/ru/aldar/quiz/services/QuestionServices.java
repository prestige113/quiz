package ru.aldar.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aldar.quiz.domain.Question;
import ru.aldar.quiz.repository.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionServices {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> list() {
        return questionRepository.findAll();
    }

    public Question get(Long id) {
        return questionRepository.getOne(id);
    }

    public Question create(Question question) {
        return questionRepository.saveAndFlush(question);
    }

    public Question update(Question question) {
        return questionRepository.saveAndFlush(question);
    }

    public String delete(Long id) {
        Question question = questionRepository.getOne(id);
        if (question != null) {
            questionRepository.delete(question);
            return "Deleted";
        }
        return "Not Found!";
    }
}
