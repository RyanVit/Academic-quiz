package br.com.academicquiz.quiz.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.academicquiz.alternatives.models.AlternativesModel;
import br.com.academicquiz.helpers.ErrorMessage;
import br.com.academicquiz.question.models.QuestionModel;
import br.com.academicquiz.question.services.QuestionService;
import br.com.academicquiz.quiz.dtos.QuizRequestDto;
import br.com.academicquiz.quiz.utils.QuizQuestionStructure;
import br.com.academicquiz.quiz.utils.QuizStructure;
import br.com.academicquiz.theme.models.ThemeModel;
import br.com.academicquiz.theme.services.ThemeService;

@RestController()
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuestionService quizQuestionService;

    @Autowired
    private ThemeService themeService;

    @PostMapping()
    public ResponseEntity<Object> AnalyzeResponseController(@RequestBody QuizRequestDto request) {
        try {

            if (request.getThemeId() == null) {
                return ResponseEntity.badRequest().body(new ErrorMessage("obrigatory theme."));
            }

            ThemeModel theme = themeService.getThemeId(request.getThemeId());
            if (theme == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Theme not found."));
            }

            if (theme.getQuestions().size() != request.getQuestions().size()) {
                return ResponseEntity.status(400).body(new ErrorMessage("Questions not found."));
            }

            List<QuizQuestionStructure> questionsStructure = new ArrayList<>();

            for (QuizRequestDto.ResponseQuestion question : request.getQuestions()) {
                if (question.getQuestionId() == null || question.getAlternativeId() == null) {
                    ResponseEntity.status(400).body(new ErrorMessage("obrigatory question."));
                    break;
                }

                QuestionModel questionModel = quizQuestionService.getQuestion(question.getQuestionId());
                if (questionModel == null) {
                    return ResponseEntity.status(404).body(new ErrorMessage("Question not found."));
                }

                AlternativesModel alternativeModel = questionModel.getAlternatives().stream()
                        .filter(alternative -> alternative.getId().equals(question.getAlternativeId())).findFirst()
                        .orElse(null);

                if (alternativeModel == null) {
                    return ResponseEntity.status(404).body(new ErrorMessage("Alternative not found."));
                }
                QuizQuestionStructure questionStructure = new QuizQuestionStructure();
                if (alternativeModel.isCorrect()) {
                    questionStructure.setCorrect(true);
                    questionStructure.setAlternativeCorrectId(alternativeModel.getId());
                } else {
                    AlternativesModel alternativeCorrect = questionModel.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst()
                            .orElse(null);

                    questionStructure.setAlternativeCorrectId(alternativeCorrect.getId());
                    questionStructure.setCorrect(false);

                }
                questionStructure.setQuestionid(question.getQuestionId());
                questionStructure.setAlternativeCorrectId(question.getAlternativeId());
                questionsStructure.add(questionStructure);

            }

            QuizStructure quizStructure = new QuizStructure();
            quizStructure.setQuestions(questionsStructure);
            quizStructure.setThemeId(theme.getId());

            return ResponseEntity.status(200).body(quizStructure);

        } catch (Exception e) {

            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }

    }

}