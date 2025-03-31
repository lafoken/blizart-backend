package ua.com.blizartproduction.infohub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerEnvironmentDto {
    private String description;
    private List<BenefitDto> benefits;
}