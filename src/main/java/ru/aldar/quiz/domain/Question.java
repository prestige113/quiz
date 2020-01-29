package ru.aldar.quiz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "quest_body")
    private String questBody;
    @Column(name = "order")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
