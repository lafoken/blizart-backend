package ua.com.blizartproduction.infohub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.blizartproduction.infohub.dto.CareerEnvironmentDto;
import ua.com.blizartproduction.infohub.dto.VacancyDto;
import ua.com.blizartproduction.infohub.service.CareerService;
import java.util.List;

@RestController
@RequestMapping("/api/careers") // Базовий шлях для публічних API кар'єри
@RequiredArgsConstructor
public class CareerController {

    private final CareerService careerService;

    @GetMapping("/environment")
    public ResponseEntity<CareerEnvironmentDto> getCareerEnvironment() {
        CareerEnvironmentDto environment = careerService.getCareerEnvironment();
        return ResponseEntity.ok(environment);
    }

    @GetMapping("/vacancies")
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        // Тепер викликаємо метод, що повертає тільки активні вакансії
        List<VacancyDto> vacancies = careerService.getActiveVacancies();
        return ResponseEntity.ok(vacancies);
    }
}