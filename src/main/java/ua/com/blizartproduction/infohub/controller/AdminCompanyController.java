package ua.com.blizartproduction.infohub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.blizartproduction.infohub.dto.CompanyDetailsDto;
import ua.com.blizartproduction.infohub.service.CompanyService;

@RestController
@RequestMapping("/api/admin/company")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')") // Захист ендпоінтів
public class AdminCompanyController {

    private final CompanyService companyService;

    @PutMapping("/details")
    public ResponseEntity<CompanyDetailsDto> updateCompanyDetails(@Valid @RequestBody CompanyDetailsDto dto) {
        CompanyDetailsDto updatedDto = companyService.updateCompanyDetails(dto);
        return ResponseEntity.ok(updatedDto);
    }
}