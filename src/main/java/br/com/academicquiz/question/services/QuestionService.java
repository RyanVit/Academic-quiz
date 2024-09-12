package br.com.academicquiz.question.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.academicquiz.question.models.QuestionModel;
import br.com.academicquiz.question.repositories.QuestionRepository;

import br.com.academicquiz.theme.models.ThemeModel;


@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

 
    public QuestionModel createQuestion(ThemeModel theme, String statement) {
        QuestionModel question = new QuestionModel();
        question.setStatement(statement);
        question.setTheme(theme);

        return repository.save(question);
    }

    public QuestionModel getQuestion(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<QuestionModel> allQuestions(long themeId) {
        return repository.findAll(themeId);
    }

    public QuestionModel updateQuestion(QuestionModel question) {
        return repository.save(question);
    }

    public void deleteQuestion(QuestionModel question) {
        repository.delete(question);
    }
}
