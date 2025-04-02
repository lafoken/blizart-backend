package ua.com.blizartproduction.infohub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.blizartproduction.infohub.dto.CompanyDetailsDto;
import ua.com.blizartproduction.infohub.dto.ParentCompanyDto;
import ua.com.blizartproduction.infohub.entity.CompanyDetails;
import ua.com.blizartproduction.infohub.repository.CompanyDetailsRepository;
import ua.com.blizartproduction.infohub.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDetailsRepository companyDetailsRepository;
    private static final Long SINGLE_RECORD_ID = 1L;

    @Override
    @Transactional(readOnly = true)
    public CompanyDetailsDto getCompanyDetails() {
        CompanyDetails entity = findSingleRecord();
        return mapEntityToDto(entity);
    }

    @Override
    @Transactional
    public CompanyDetailsDto updateCompanyDetails(CompanyDetailsDto dto) {
        CompanyDetails entity = findSingleRecord();
        mapDtoToEntity(dto, entity);
        CompanyDetails updatedEntity = companyDetailsRepository.save(entity);
        return mapEntityToDto(updatedEntity);
    }

    private CompanyDetails findSingleRecord() {
        return companyDetailsRepository.findById(SINGLE_RECORD_ID)
                .orElseThrow(() -> new ResourceNotFoundException("Company details record not found"));
    }

    private CompanyDetailsDto mapEntityToDto(CompanyDetails entity) {
        ParentCompanyDto parentDto = new ParentCompanyDto(
                entity.getParentCompanyName(),
                entity.getParentCompanyAddress(),
                entity.getParentCompanyWebsiteUrl()
        );
        return new CompanyDetailsDto(
                entity.getFullName(), entity.getEdrpou(), entity.getLegalAddress(),
                entity.getDirectorName(), entity.getFoundingDate(), entity.getHistorySummary(),
                entity.getStatus(), parentDto
        );
    }

    private void mapDtoToEntity(CompanyDetailsDto dto, CompanyDetails entity) {
        entity.setFullName(dto.getFullName());
        entity.setEdrpou(dto.getEdrpou());
        entity.setLegalAddress(dto.getLegalAddress());
        entity.setDirectorName(dto.getDirectorName());
        entity.setFoundingDate(dto.getFoundingDate());
        entity.setHistorySummary(dto.getHistorySummary());
        entity.setStatus(dto.getStatus());
        entity.setParentCompanyName(dto.getParentCompany() != null ? dto.getParentCompany().getName() : null);
        entity.setParentCompanyAddress(dto.getParentCompany() != null ? dto.getParentCompany().getAddress() : null);
        entity.setParentCompanyWebsiteUrl(dto.getParentCompany() != null ? dto.getParentCompany().getWebsiteUrl() : null);
    }
}