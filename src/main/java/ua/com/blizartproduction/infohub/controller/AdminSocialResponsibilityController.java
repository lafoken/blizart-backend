package ua.com.blizartproduction.infohub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.blizartproduction.infohub.dto.SocialResponsibilityInfoDto;
import ua.com.blizartproduction.infohub.service.SocialResponsibilityService;

@RestController
@RequestMapping("/api/admin/social-responsibility")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminSocialResponsibilityController {

    private final SocialResponsibilityService socialResponsibilityService;

    @PutMapping("/info")
    public ResponseEntity<SocialResponsibilityInfoDto> updateSocialResponsibilityInfo(@Valid @RequestBody SocialResponsibilityInfoDto dto) {
        SocialResponsibilityInfoDto updatedDto = socialResponsibilityService.updateSocialResponsibilityInfo(dto);
        return ResponseEntity.ok(updatedDto);
    }
}