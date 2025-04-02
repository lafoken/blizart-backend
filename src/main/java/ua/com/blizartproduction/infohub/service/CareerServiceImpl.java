package ua.com.blizartproduction.infohub.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.blizartproduction.infohub.dto.BenefitDto;
import ua.com.blizartproduction.infohub.dto.CareerEnvironmentDto;
import ua.com.blizartproduction.infohub.dto.VacancyDto;
import ua.com.blizartproduction.infohub.entity.Benefit;
import ua.com.blizartproduction.infohub.entity.CareerEnvironment;
import ua.com.blizartproduction.infohub.entity.Vacancy;
import ua.com.blizartproduction.infohub.repository.CareerEnvironmentRepository;
import ua.com.blizartproduction.infohub.repository.VacancyRepository;
import ua.com.blizartproduction.infohub.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {

    private static final Logger log = LoggerFactory.getLogger(CareerServiceImpl.class);
    private final CareerEnvironmentRepository careerEnvironmentRepository;
    private final VacancyRepository vacancyRepository;
    private static final Long SINGLE_ENV_ID = 1L;
    private static final String LIST_DELIMITER = ";";

    @Override
    @Transactional(readOnly = true)
    public CareerEnvironmentDto getCareerEnvironment() {
        CareerEnvironment entity = findSingleEnvRecord();
        return mapEnvironmentEntityToDto(entity);
    }

    @Override
    @Transactional
    public CareerEnvironmentDto updateCareerEnvironment(CareerEnvironmentDto dto) {
        CareerEnvironment entity = findSingleEnvRecord();
        entity.setDescription(dto.getDescription());
        entity.getBenefits().clear();
        if (dto.getBenefits() != null) {
            dto.getBenefits().forEach(benefitDto -> {
                Benefit benefit = new Benefit();
                mapBenefitDtoToEntity(benefitDto, benefit);
                benefit.setCareerEnvironment(entity);
                entity.getBenefits().add(benefit);
            });
        }
        CareerEnvironment updatedEntity = careerEnvironmentRepository.save(entity);
        return mapEnvironmentEntityToDto(updatedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacancyDto> getActiveVacancies() {
        log.info("Fetching active vacancies");
        return vacancyRepository.findByStatusOrderByIdAsc(Vacancy.Status.ACTIVE).stream()
                .map(this::mapVacancyEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacancyDto> getAllAdminVacancies() {
        log.info("Fetching all vacancies for admin");
        return vacancyRepository.findAllByOrderByIdAsc().stream()
                .map(this::mapVacancyEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public VacancyDto addVacancy(VacancyDto dto) {
        Vacancy entity = new Vacancy();
        mapVacancyDtoToEntity(dto, entity);
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(Vacancy.Status.ACTIVE); // Встановлюємо статус за замовчуванням
        log.info("Adding new vacancy with generated ID: {}", entity.getId());
        Vacancy savedEntity = vacancyRepository.save(entity);
        return mapVacancyEntityToDto(savedEntity);
    }

    @Override
    @Transactional
    public VacancyDto updateVacancy(String id, VacancyDto dto) {
        log.info("Updating vacancy with ID: {}", id);
        Vacancy entity = vacancyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Vacancy not found with id: {}", id);
                    return new ResourceNotFoundException("Vacancy not found with id: " + id);
                });

        mapVacancyDtoToEntity(dto, entity); // Переносимо мапінг в хелпер
        entity.setId(id); // Переконуємося, що ID залишається тим самим

        Vacancy updatedEntity = vacancyRepository.save(entity);
        log.info("Vacancy updated successfully with ID: {}", id);
        return mapVacancyEntityToDto(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteVacancy(String id) {
        log.info("Deleting vacancy with ID: {}", id);
        if (!vacancyRepository.existsById(id)) {
            log.error("Vacancy not found for deletion with id: {}", id);
            throw new ResourceNotFoundException("Vacancy not found with id: " + id);
        }
        vacancyRepository.deleteById(id);
        log.info("Vacancy deleted successfully with ID: {}", id);
    }

    private CareerEnvironment findSingleEnvRecord() {
        return careerEnvironmentRepository.findById(SINGLE_ENV_ID)
                .orElseThrow(() -> new ResourceNotFoundException("Career environment record not found"));
    }

    private void mapBenefitDtoToEntity(BenefitDto dto, Benefit entity) {
        entity.setIcon(dto.getIcon());
        entity.setTitle(dto.getTitle());
        entity.setDetails(dto.getDetails());
    }

    private CareerEnvironmentDto mapEnvironmentEntityToDto(CareerEnvironment entity) {
        List<BenefitDto> benefitDtos = Optional.ofNullable(entity.getBenefits())
                .orElseGet(Collections::emptyList).stream()
                .map(b -> new BenefitDto(b.getIcon(), b.getTitle(), b.getDetails()))
                .collect(Collectors.toList());
        return new CareerEnvironmentDto(entity.getDescription(), benefitDtos);
    }

    private VacancyDto mapVacancyEntityToDto(Vacancy entity) {
        List<String> requirements = Optional.ofNullable(entity.getRequirements())
                .filter(s -> !s.isBlank())
                .map(s -> Arrays.asList(s.split(LIST_DELIMITER)))
                .orElseGet(Collections::emptyList);

        return new VacancyDto(
                entity.getId(),
                entity.getTitle(),
                entity.getShortDescription(),
                requirements,
                entity.getStatus(),
                entity.getAssignedTo(),
                entity.getFilledDate() != null ? entity.getFilledDate().toString() : null, // Конвертуємо LocalDate в String
                entity.getArchivedDate() != null ? entity.getArchivedDate().toString() : null // Конвертуємо LocalDate в String
        );
    }

    private void mapVacancyDtoToEntity(VacancyDto dto, Vacancy entity) {
        entity.setTitle(dto.getTitle());
        entity.setShortDescription(dto.getShortDescription());
        entity.setRequirements(dto.getRequirements() != null ? String.join(LIST_DELIMITER, dto.getRequirements()) : null);
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : Vacancy.Status.ACTIVE); // Встановлюємо статус
        entity.setAssignedTo(dto.getAssignedTo());

        // Обробка дат
        entity.setFilledDate(parseLocalDate(dto.getFilledDate()));
        entity.setArchivedDate(parseLocalDate(dto.getArchivedDate()));
    }

    private LocalDate parseLocalDate(String dateString) {
        if (dateString == null || dateString.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(dateString); // Очікує формат YYYY-MM-DD
        } catch (DateTimeParseException e) {
            log.warn("Could not parse date string '{}'. Expected format YYYY-MM-DD. Error: {}", dateString, e.getMessage());
            return null; // Або викидати виняток, якщо некоректний формат є критичним
        }
    }
}