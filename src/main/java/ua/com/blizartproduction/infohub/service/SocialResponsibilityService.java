package ua.com.blizartproduction.infohub.service;

import ua.com.blizartproduction.infohub.dto.SocialResponsibilityInfoDto;

public interface SocialResponsibilityService {
    SocialResponsibilityInfoDto getSocialResponsibilityInfo();
    SocialResponsibilityInfoDto updateSocialResponsibilityInfo(SocialResponsibilityInfoDto infoDto); // Додано
}