package br.com.academicquiz.question.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.academicquiz.alternatives.dtos.AlternativeRequestDto.AlternativeRequest;
import br.com.academicquiz.alternatives.dtos.AlternativeRequestDto.AlternativeRequestUpdate;
import br.com.academicquiz.alternatives.models.AlternativesModel;
import br.com.academicquiz.alternatives.services.AlternativeService;
import br.com.academicquiz.alternatives.utils.AlternativeStructore;
import br.com.academicquiz.helpers.ErrorMessage;
import br.com.academicquiz.question.dtos.QuestionRequestDto.QuestionRequest;
import br.com.academicquiz.question.dtos.QuestionRequestDto.QuestionRequestUpdate;
import br.com.academicquiz.question.models.QuestionModel;
import br.com.academicquiz.question.services.QuestionService;
import br.com.academicquiz.question.utils.QuestionStructure;

import br.com.academicquiz.theme.models.ThemeModel;
import br.com.academicquiz.theme.services.ThemeService;



@RestController()
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private AlternativeService alternativeService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private QuestionService questionService;

    @PostMapping()
    public ResponseEntity<Object> createQuestion(@RequestBody QuestionRequest request) {
        try {
            // validations
            if (request.getStatement() == null || request.getStatement().trim().isEmpty()) {
                return ResponseEntity.status(400).body(new ErrorMessage("Statement cannot be empty"));
            }

            if (request.getThemeId() == null) {
                return ResponseEntity.status(400).body(new ErrorMessage("Theme id cannot be empty"));
            }

            if (request.getAlternatives().isEmpty()) {
                return ResponseEntity.status(400).body(new ErrorMessage("Alternatives cannot be empty"));
            }
            // check if theme exists
            ThemeModel theme = themeService.getThemeId(request.getThemeId());
            if (theme == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Theme not found"));
            }
            // check if there is at least one correct alternative
            int countIsCorrect = 0;
            for (AlternativeRequest alternative : request.getAlternatives()) {
                if (alternative.getDescription().trim().isEmpty()) {
                    ResponseEntity.status(400).body(new ErrorMessage("Alternative cannot be empty"));
                    break;
                }

                if (alternative.getIsCorrect() == false) {
                    countIsCorrect++;
                }
            }
            // count is correct must be at least 1
            if (countIsCorrect == request.getAlternatives().size()) {
                return ResponseEntity.status(400)
                        .body(new ErrorMessage("There must be at least one correct alternative"));
            }

            // create question in database
            QuestionModel createdQuestion = this.questionService.createQuestion(theme, request.getStatement());

            // create alternatives in database
            for (AlternativeRequest alternative : request.getAlternatives()) {
                alternativeService.createAlternative(createdQuestion, alternative.getDescription(),
                        alternative.getIsCorrect());
            }
            return ResponseEntity.status(200).body(new ErrorMessage("Question created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getQuestion(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(400).body(new ErrorMessage("obrigatory question"));
            }
            // check if question exists
            QuestionModel question = questionService.getQuestion(id);

            if (question == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Question not found"));
            }
            // get alternatives from database and map to dto structure
            List<AlternativeStructore> alternativesStructores = question.getAlternatives().stream()
                    .map(alternative -> {
                        return new AlternativeStructore(alternative.getId(),
                                alternative.getDescription(), alternative.getQuestion().getId());
                    }).toList();

            // create dto structure and return response
            QuestionStructure questionStructure = new QuestionStructure(
                    question.getId(), question.getStatement(), question.getTheme().getId(), alternativesStructores);

            return ResponseEntity.status(200).body(questionStructure);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getQuestions(@RequestParam Long themeId) {
        try {
            // get all questions by theme id
            List<QuestionModel> questions = questionService.allQuestions(themeId);
            // map to dto structure and return response
            List<QuestionStructure> questionsStructure = questions.stream( )
                    .map(question -> {
                        List<AlternativeStructore> alternativesStructores = question.getAlternatives().stream()
                                .map(alternative -> {
                                    return new AlternativeStructore(alternative.getId(),
                                            alternative.getDescription(), alternative.getQuestion().getId());
                                }).toList();
                        return new QuestionStructure(question.getId(), question.getStatement(),
                                question.getTheme().getId(),
                                alternativesStructores);
                    }).toList();

            return ResponseEntity.status(200).body(questionsStructure);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateQuestion(@PathVariable Long id, @RequestBody QuestionRequestUpdate request) {
        try {
            // validations
            System.out.println("oi né" + request.getStatement());
            if (request.getStatement() == null || request.getStatement().trim().isEmpty()) {
                return ResponseEntity.status(400).body(new ErrorMessage("Statement cannot be empty"));
            }
            if (request.getAlternatives().isEmpty()) {
                return ResponseEntity.status(400).body(new ErrorMessage("Alternatives cannot be empty"));
            }
            

            QuestionModel question = questionService.getQuestion(id);
            if (question == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Question not found"));
            }

            List < AlternativesModel > saveVAlidateAlternative = new  ArrayList<>();
            int countIsCorrect = 0;
            // check if there is at least one correct alternative

            for (AlternativeRequestUpdate alternative : request.getAlternatives()) {    
                System.out.println("oi né" + alternative.getId());
                if (alternative.getDescription().trim().isEmpty()) {
                    ResponseEntity.status(400).body(new ErrorMessage("Alternative cannot be empty"));
                    break;
                }
                
                AlternativesModel verifyAlternativeExist = question.getAlternatives().stream()
                .filter(a-> a.getId().equals(alternative.getId())  ).findFirst().orElse(null);
              
                if (verifyAlternativeExist == null) {
                    ResponseEntity.status(400).body(new ErrorMessage("Alternative not found"));
                    break;
                }

                verifyAlternativeExist.setDescription(alternative.getDescription());
                verifyAlternativeExist.setIsCorrect(alternative.getIsCorrect());
                saveVAlidateAlternative.add(verifyAlternativeExist);

                if (alternative.getIsCorrect() == false) {
                    countIsCorrect++;
                }
            }
            // count is correct must be at least 1
            if (countIsCorrect == request.getAlternatives().size()) {
                return ResponseEntity.status(400)
                        .body(new ErrorMessage("There must be at least one correct alternative"));
            }

            // update question in database
            if (saveVAlidateAlternative.size() != request.getAlternatives().size()) {
                return ResponseEntity.status(400).body(new ErrorMessage("Alternative not found"));
            }

            saveVAlidateAlternative.forEach(alternative -> {
                alternativeService.update(alternative);
            });

            question.setStatement(request.getStatement());
            questionService.updateQuestion(question);
            return ResponseEntity.status(200).body(new ErrorMessage("Question updated successfully"));
                

        } catch (Exception e) { 
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }
    }   // check if question exists

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable Long id) {
        try {
            // check if question exists
            QuestionModel question = questionService.getQuestion(id);
            if (question == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Question not found"));
            }
            // delete question
            questionService.deleteQuestion(question);
            return ResponseEntity.status(200).body(new ErrorMessage("Question deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }
    }

    // TO Do:  update logic

}

