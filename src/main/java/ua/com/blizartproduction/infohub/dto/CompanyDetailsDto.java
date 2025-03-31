package ua.com.blizartproduction.infohub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailsDto {
    private String fullName;
    private String edrpou;
    private String legalAddress;
    private String directorName;
    private String foundingDate;
    private String historySummary;
    private String status;
    private ParentCompanyDto parentCompany;
}