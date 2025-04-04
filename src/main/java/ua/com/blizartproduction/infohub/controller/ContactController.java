package ua.com.blizartproduction.infohub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.blizartproduction.infohub.dto.ContactFormDto;
import ua.com.blizartproduction.infohub.dto.ApiResponseDto;
import ua.com.blizartproduction.infohub.service.ContactService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/submit")
    public ResponseEntity<ApiResponseDto> submitContactForm(@Valid @RequestBody ContactFormDto formData) {
        ApiResponseDto response = contactService.handleContactForm(formData);
        return ResponseEntity.ok(response);
    }
}