package ua.com.blizartproduction.infohub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.blizartproduction.infohub.dto.SocialResponsibilityInfoDto;
import ua.com.blizartproduction.infohub.service.SocialResponsibilityService;

@RestController
@RequestMapping("/api/social-responsibility")
@RequiredArgsConstructor
public class SocialResponsibilityController {

    private final SocialResponsibilityService socialResponsibilityService;

    @GetMapping("/info")
    public ResponseEntity<SocialResponsibilityInfoDto> getSocialResponsibilityInfo() {
        SocialResponsibilityInfoDto info = socialResponsibilityService.getSocialResponsibilityInfo();
        return ResponseEntity.ok(info);
    }
}