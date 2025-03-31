package ua.com.blizartproduction.infohub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialResponsibilityInfoDto {
    private String introduction;
    private SocialResponsibilitySectionDto localImpact;
    private SocialResponsibilitySectionDto economicContribution;
    private SocialResponsibilitySectionDto employeeSupport;
}