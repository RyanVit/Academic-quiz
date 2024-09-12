package br.com.academicquiz.alternatives.dtos;


public class AlternativeRequestDto {
    public static class AlternativeRequest {
        private String description;
        private boolean isCorrect;
        

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean getIsCorrect() {
            return isCorrect;
        }

        public void setCorrect(boolean correct) {
            isCorrect = correct;
        }


        
    }

    public static class AlternativeRequestUpdate {
        private Long id;
        private String description;
        private boolean isCorrect;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean getIsCorrect() {
            return isCorrect;
        }

        public void setCorrect(boolean correct) {
            isCorrect = correct;
        }
    }
}
