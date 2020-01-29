package ru.aldar.quiz.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
@ApiModel(value = "quiz")
@Api(value = "/contract", tags = "quiz-resource")
@SwaggerDefinition(tags = {
        @Tag(name="quiz-resource", description = "Сервис управления опросами")
})
public class QuizResource {

}
