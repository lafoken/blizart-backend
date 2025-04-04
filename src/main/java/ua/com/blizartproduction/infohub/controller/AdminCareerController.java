package ua.com.blizartproduction.infohub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.blizartproduction.infohub.dto.CareerEnvironmentDto;
import ua.com.blizartproduction.infohub.dto.VacancyDto;
import ua.com.blizartproduction.infohub.service.CareerService;

import java.util.List; // Імпортуємо List

@RestController
@RequestMapping("/api/admin/careers")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminCareerController {

    private final CareerService careerService;

    @PutMapping("/environment")
    public ResponseEntity<CareerEnvironmentDto> updateCareerEnvironment(@Valid @RequestBody CareerEnvironmentDto dto) {
        CareerEnvironmentDto updatedDto = careerService.updateCareerEnvironment(dto);
        return ResponseEntity.ok(updatedDto);
    }

    // Новий ендпоінт для отримання ВСІХ вакансій для адмінки
    @GetMapping("/vacancies")
    public ResponseEntity<List<VacancyDto>> getAllVacanciesForAdmin() {
        List<VacancyDto> vacancies = careerService.getAllAdminVacancies();
        return ResponseEntity.ok(vacancies);
    }

    @PostMapping("/vacancies")
    public ResponseEntity<VacancyDto> addVacancy(@Valid @RequestBody VacancyDto dto) {
        VacancyDto createdDto = careerService.addVacancy(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/vacancies/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable String id, @Valid @RequestBody VacancyDto dto) {
        // Тепер DTO містить нові поля (status, assignedTo, etc.)
        VacancyDto updatedDto = careerService.updateVacancy(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/vacancies/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable String id) {
        careerService.deleteVacancy(id);
        return ResponseEntity.noContent().build();
    }
}