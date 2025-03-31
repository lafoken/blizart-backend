package ua.com.blizartproduction.infohub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialResponsibilitySectionDto {
    private String description;
    private List<String> keyPoints;
    private List<AreaDto> areas;
    private List<String> programs;
}