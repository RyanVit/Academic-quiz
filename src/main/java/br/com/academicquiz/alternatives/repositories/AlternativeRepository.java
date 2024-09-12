package br.com.academicquiz.alternatives.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academicquiz.alternatives.models.AlternativesModel;
import br.com.academicquiz.question.models.QuestionModel;

public interface AlternativeRepository extends JpaRepository<AlternativesModel, Long> {

    public List<AlternativesModel> findByQuestion(QuestionModel question);
}
