package br.com.academicquiz.question.dtos;

import java.util.ArrayList;
import java.util.List;


import br.com.academicquiz.alternatives.dtos.AlternativeRequestDto.AlternativeRequest;
import br.com.academicquiz.alternatives.dtos.AlternativeRequestDto.AlternativeRequestUpdate;

public class QuestionRequestDto {
    public static class QuestionRequest {
        
        private String statement;
        private Long themeId;
        List<AlternativeRequest> alternatives = new ArrayList<>();

        public String getStatement() {
            return statement;
        }

        public void setStatement(String statement) {
            this.statement = statement;
        }

        public Long getThemeId() {
            return themeId;
        }

        public void setThemeId(Long themeId) {
            this.themeId = themeId;
        }

        public List<AlternativeRequest> getAlternatives() {
            return alternatives;
        }

        public void setAlternatives(List<AlternativeRequest> alternatives) {
            this.alternatives = alternatives;
        }

    }

    public static class QuestionRequestUpdate {
        private String statement;
        private List<AlternativeRequestUpdate> alternatives = new ArrayList<>();

        public String getStatement() {
            return statement;
        }

        public void setStatement(String statement) {
            this.statement = statement;
        }

        public List<AlternativeRequestUpdate> getAlternatives() {
            return alternatives;
        }

        public void setAlternatives(List<AlternativeRequestUpdate> alternatives) {
            this.alternatives = alternatives;
        }

    }
}
