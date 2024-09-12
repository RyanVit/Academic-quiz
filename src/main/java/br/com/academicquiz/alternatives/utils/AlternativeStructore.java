package br.com.academicquiz.alternatives.utils;

public class AlternativeStructore {
    private Long id;
    private String description;
    private Long questionId;

    public AlternativeStructore(Long id, String description, Long questionId) {
        this.description = description;
        this.questionId = questionId;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

}
