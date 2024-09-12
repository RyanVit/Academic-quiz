package br.com.academicquiz.quiz.dtos;



import java.util.List;



public class QuizRequestDto {
    private Long themeId;
    private List<ResponseQuestion> questions ;


    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public List<ResponseQuestion> getQuestions() {
        return questions;
    }   

    public void setQuestions(List<ResponseQuestion> questions) {
        this.questions = questions;
    }

    public static class ResponseQuestion{
        private Long questionId;
        private Long alternativeId;

        public Long getQuestionId() {
            return questionId;
        }   

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public Long getAlternativeId() {
            return alternativeId;
        }

        public void setAlternativeId(Long alternativeId) {
            this.alternativeId = alternativeId;
        }


    }
}