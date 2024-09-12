package br.com.academicquiz.theme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academicquiz.theme.models.ThemeModel;
import br.com.academicquiz.theme.repositories.ThemeRepository;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository repository;

    public ThemeModel createTheme(String name) {
        ThemeModel theme = new ThemeModel();
        theme.setName(name);
        return repository.save(theme);
    }

    public ThemeModel getThemeId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ThemeModel getThemeName(String name) {
        return repository.findByName(name);
    }

    public List<ThemeModel> allThemes() {
        return repository.findAll();
    }

    public ThemeModel updateTheme(ThemeModel theme) {
        return repository.save(theme);
    }

    public void deleteTheme(Long id) {
        repository.deleteById(id);
    }

}
