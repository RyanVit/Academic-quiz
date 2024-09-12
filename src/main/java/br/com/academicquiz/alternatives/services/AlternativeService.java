package br.com.academicquiz.alternatives.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academicquiz.alternatives.models.AlternativesModel;
import br.com.academicquiz.alternatives.repositories.AlternativeRepository;
import br.com.academicquiz.question.models.QuestionModel;


@Service
public class AlternativeService {

    @Autowired
    private AlternativeRepository repository;

    public AlternativesModel createAlternative(QuestionModel question, String description, boolean isCorrect) {
        AlternativesModel alternative = new AlternativesModel();
        alternative.setQuestion(question);
        alternative.setDescription(description);
        alternative.setIsCorrect(isCorrect);
        return repository.save(alternative);
    }

    public 
    br.com.academicquiz.alternatives.models.AlternativesModel getAlternative(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<
    AlternativesModel> allAlternatives() {
        return repository.findAll();
    }

    public 
    br.com.academicquiz.alternatives.models.AlternativesModel update(
        AlternativesModel alternative) {
        return repository.save(alternative);
    }

    public void deleteAlternative(
        AlternativesModel alternative) {
        repository.delete(alternative);
    }
}
