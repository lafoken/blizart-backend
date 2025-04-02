package ua.com.blizartproduction.infohub.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.blizartproduction.infohub.dto.AreaDto;
import ua.com.blizartproduction.infohub.dto.SocialResponsibilityInfoDto;
import ua.com.blizartproduction.infohub.dto.SocialResponsibilitySectionDto;
import ua.com.blizartproduction.infohub.entity.SocialResponsibilityInfo;
import ua.com.blizartproduction.infohub.repository.SocialResponsibilityInfoRepository;
import ua.com.blizartproduction.infohub.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialResponsibilityServiceImpl implements SocialResponsibilityService {

    private final SocialResponsibilityInfoRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(SocialResponsibilityServiceImpl.class);
    private static final Long SINGLE_RECORD_ID = 1L;
    private static final String LIST_DELIMITER = ";";

    @Override
    @Transactional(readOnly = true)
    public SocialResponsibilityInfoDto getSocialResponsibilityInfo() {
        SocialResponsibilityInfo entity = findSingleRecord();
        return mapEntityToDto(entity);
    }

    @Override
    @Transactional
    public SocialResponsibilityInfoDto updateSocialResponsibilityInfo(SocialResponsibilityInfoDto dto) {
        SocialResponsibilityInfo entity = findSingleRecord();
        mapDtoToEntity(dto, entity);
        SocialResponsibilityInfo updatedEntity = repository.save(entity);
        return mapEntityToDto(updatedEntity);
    }


    private SocialResponsibilityInfo findSingleRecord() {
        return repository.findById(SINGLE_RECORD_ID)
                .orElseThrow(() -> new ResourceNotFoundException("Social responsibility info record not found"));
    }

    private SocialResponsibilityInfoDto mapEntityToDto(SocialResponsibilityInfo entity) {
        List<String> localKeyPoints = parseListString(entity.getLocalImpactKeyPoints());
        List<AreaDto> economicAreas = parseAreasJson(entity.getEconomicContributionAreas());
        List<String> supportPrograms = parseListString(entity.getEmployeeSupportPrograms());

        SocialResponsibilitySectionDto localImpact = new SocialResponsibilitySectionDto(
                entity.getLocalImpactDescription(), localKeyPoints, null, null);
        SocialResponsibilitySectionDto economicContribution = new SocialResponsibilitySectionDto(
                entity.getEconomicContributionDescription(), null, economicAreas, null);
        SocialResponsibilitySectionDto employeeSupport = new SocialResponsibilitySectionDto(
                entity.getEmployeeSupportDescription(), null, null, supportPrograms);

        return new SocialResponsibilityInfoDto(
                entity.getIntroduction(), localImpact, economicContribution, employeeSupport);
    }

    private void mapDtoToEntity(SocialResponsibilityInfoDto dto, SocialResponsibilityInfo entity) {
        entity.setIntroduction(dto.getIntroduction());
        entity.setLocalImpactDescription(dto.getLocalImpact() != null ? dto.getLocalImpact().getDescription() : null);
        entity.setLocalImpactKeyPoints(dto.getLocalImpact() != null && dto.getLocalImpact().getKeyPoints() != null
                ? String.join(LIST_DELIMITER, dto.getLocalImpact().getKeyPoints())
                : null);
        entity.setEconomicContributionDescription(dto.getEconomicContribution() != null ? dto.getEconomicContribution().getDescription() : null);
        entity.setEconomicContributionAreas(serializeAreasToJson(dto.getEconomicContribution() != null ? dto.getEconomicContribution().getAreas() : null));
        entity.setEmployeeSupportDescription(dto.getEmployeeSupport() != null ? dto.getEmployeeSupport().getDescription() : null);
        entity.setEmployeeSupportPrograms(dto.getEmployeeSupport() != null && dto.getEmployeeSupport().getPrograms() != null
                ? String.join(LIST_DELIMITER, dto.getEmployeeSupport().getPrograms())
                : null);
    }

    private List<String> parseListString(String data) {
        if (data == null || data.isBlank()) return Collections.emptyList();
        return Arrays.stream(data.split(LIST_DELIMITER)).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    private List<AreaDto> parseAreasJson(String json) {
        if (json == null || json.isBlank()) return Collections.emptyList();
        try {
            return objectMapper.readValue(json, new TypeReference<List<AreaDto>>() {});
        } catch (IOException e) {
            log.error("Failed to parse economic contribution areas JSON: {}", json, e);
            return Collections.emptyList();
        }
    }

    private String serializeAreasToJson(List<AreaDto> areas) {
        if (areas == null || areas.isEmpty()) return "[]";
        try {
            return objectMapper.writeValueAsString(areas);
        } catch (IOException e) {
            log.error("Failed to serialize economic contribution areas to JSON", e);
            return "[]";
        }
    }
}