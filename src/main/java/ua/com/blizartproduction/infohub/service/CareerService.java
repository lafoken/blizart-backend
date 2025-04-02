package ua.com.blizartproduction.infohub.service;

import ua.com.blizartproduction.infohub.dto.CareerEnvironmentDto;
import ua.com.blizartproduction.infohub.dto.VacancyDto;
import java.util.List;

public interface CareerService {
    CareerEnvironmentDto getCareerEnvironment();
    CareerEnvironmentDto updateCareerEnvironment(CareerEnvironmentDto careerEnvironmentDto);

    // Метод для публічного сайту - повертає тільки активні
    List<VacancyDto> getActiveVacancies();

    // Метод для адмін-панелі - повертає всі вакансії
    List<VacancyDto> getAllAdminVacancies();

    VacancyDto addVacancy(VacancyDto vacancyDto);
    VacancyDto updateVacancy(String id, VacancyDto vacancyDto);
    void deleteVacancy(String id);
}