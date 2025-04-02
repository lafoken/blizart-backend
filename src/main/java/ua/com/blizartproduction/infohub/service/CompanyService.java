package ua.com.blizartproduction.infohub.service;

import ua.com.blizartproduction.infohub.dto.CompanyDetailsDto;

public interface CompanyService {
    CompanyDetailsDto getCompanyDetails();
    CompanyDetailsDto updateCompanyDetails(CompanyDetailsDto companyDetailsDto); // Додано
}