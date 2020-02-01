package ru.aldar.quiz.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.aldar.quiz.domain.Quiz;
import ru.aldar.quiz.services.QuizSearchService;
import ru.aldar.quiz.services.QuizServices;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@ApiModel(value = "quiz")
@Api(value = "/contract", tags = "quiz-resource")
@SwaggerDefinition(tags = {
        @Tag(name = "quiz-resource", description = "Сервис управления опросами")
})
public class QuizResource {

    private QuizServices quizServices;
    private QuizSearchService quizSearchService;

    @Autowired
    public QuizResource(QuizServices quizServices, QuizSearchService quizSearchService) {
        this.quizServices = quizServices;
        this.quizSearchService = quizSearchService;
    }

    /*
   TODO
   надо добить service
     */
    @GetMapping(value = "/quiz/search")
    public List<Quiz> search(@RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "start", required = false) Long startDate,
                             @RequestParam(name = "enable", required = false) Boolean enable,
                             @RequestParam(name = "sortByName") Boolean sortByName,
                             @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        return quizSearchService.search(name, startDate, enable, sortByName, page, size);
    }

    @PostMapping(value = "/quiz")
    public Quiz create(@RequestBody Quiz quiz) {
        return quizServices.create(quiz);
    }

    @GetMapping(value = "/quiz/{id}")
    public Quiz get(@PathVariable("id") Long id) {
        return quizServices.get(id);
    }

    @PutMapping(value = "/quiz")
    public Quiz update(@RequestBody Quiz quiz) {
        return quizServices.update(quiz);
    }

    @DeleteMapping(value = "/quiz/{id}")
    public String delete(@PathVariable("id") Long id) {
        return quizServices.delete(id);
    }
}
