package ua.com.blizartproduction.infohub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.blizartproduction.infohub.dto.CompanyDetailsDto;
import ua.com.blizartproduction.infohub.service.CompanyService;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/details")
    public ResponseEntity<CompanyDetailsDto> getCompanyDetails() {
        CompanyDetailsDto details = companyService.getCompanyDetails();
        return ResponseEntity.ok(details);
    }
}