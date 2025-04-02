package ua.com.blizartproduction.infohub.service;

import ua.com.blizartproduction.infohub.dto.ContactFormDto;
import ua.com.blizartproduction.infohub.dto.ApiResponseDto;

public interface ContactService {
    ApiResponseDto handleContactForm(ContactFormDto formData);
}