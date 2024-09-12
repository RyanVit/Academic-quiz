package br.com.academicquiz.question.utils;

import java.util.List;

import br.com.academicquiz.alternatives.utils.AlternativeStructore;

public class QuestionStructure {
    private Long id;
    private String statement;
    private Long themeId;
    private List<AlternativeStructore> alternatives;

    public QuestionStructure(Long id, String statement, Long themeId, List<AlternativeStructore> alternatives) {
        this.id = id;
        this.statement = statement;
        this.themeId = themeId;
        this.alternatives = alternatives;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<AlternativeStructore> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<AlternativeStructore> alternatives) {
        this.alternatives = alternatives;
    }
}