package br.com.academicquiz.quiz.utils;

import java.util.List;

public class QuizStructure {
    private Long themeId;
    private List<QuizQuestionStructure> questions;

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }
    
    public List<QuizQuestionStructure> getQuestions() {
        return questions;
    }   

    public void setQuestions(List<QuizQuestionStructure> questions) {
        this.questions = questions;
    }

    


}
