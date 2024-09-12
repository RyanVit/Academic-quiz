package br.com.academicquiz.theme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.academicquiz.helpers.ErrorMessage;
import br.com.academicquiz.theme.dtos.ThemeRequestDto.ThemeRequest;
import br.com.academicquiz.theme.models.ThemeModel;
import br.com.academicquiz.theme.services.ThemeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController()
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @PostMapping()
    public ResponseEntity<Object> createTheme(@RequestBody ThemeRequest request) {
        try {
            if (request.getName().trim().isEmpty()) {
                return ResponseEntity.status(400).body("Name cannot be empty");
            }

            ThemeModel theme = themeService.getThemeName(request.getName());

            if (theme != null) {
                return ResponseEntity.status(400).body(new ErrorMessage("Theme already exists"));
            }

            ThemeModel created = themeService.createTheme(request.getName());

            return ResponseEntity.status(201).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTheme(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(400).body(new ErrorMessage("obrigatory theme"));
            }
            System.out.println("id: " + id);
            ThemeModel theme = themeService.getThemeId(id);
            System.out.println("theme: " + theme);
            if (theme == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Theme not found"));
            }
            return ResponseEntity.status(200).body(theme);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAllThemes() {
        try {
            return ResponseEntity.status(200).body(themeService.allThemes());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTheme(@PathVariable Long id, @RequestBody ThemeRequest request) {

        try {
            if (id == null) {
                return ResponseEntity.status(400).body(new ErrorMessage("obrigatory theme"));
            }

            if (request.getName().trim().isEmpty()) {
                return ResponseEntity.status(400).body(new ErrorMessage("Name cannot be empty"));
            }

            ThemeModel theme = themeService.getThemeId(id);

            if (theme == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Theme not found"));
            }

            theme.setName(request.getName());

            return ResponseEntity.status(200).body(themeService.updateTheme(theme));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTheme(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(400).body(new ErrorMessage("obrigatory theme"));
            }

            ThemeModel theme = themeService.getThemeId(id);
            if (theme == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Theme not found"));
            }
            themeService.deleteTheme(id);

            return ResponseEntity.status(200).body(new ErrorMessage("Theme deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage(e.getMessage()));
        }
    }
}
