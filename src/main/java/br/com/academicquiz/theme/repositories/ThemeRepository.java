package br.com.academicquiz.theme.repositories;

import br.com.academicquiz.theme.models.ThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {
    ThemeModel findByName(String name);
}
