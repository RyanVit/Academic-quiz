package br.com.academicquiz.quiz.utils;

public class QuizQuestionStructure {
    private Long questionid;  
    private boolean isCorrect;
    private Long alternativeCorrectId;

    public Long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Long questionid) {
        this.questionid = questionid;
    }


    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Long getAlternativeCorrectId() {
        return alternativeCorrectId;
    }

    public void setAlternativeCorrectId(Long alternativeCorrectId) {
        this.alternativeCorrectId = alternativeCorrectId;
    }

}
