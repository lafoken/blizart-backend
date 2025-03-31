package ua.com.blizartproduction.infohub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDate;
import ua.com.blizartproduction.infohub.entity.Vacancy;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {

    private String id;

    private String title;

    private String shortDescription;

    private List<String> requirements;

    private Vacancy.Status status;

    private String assignedTo;

    private String filledDate;
    private String archivedDate;

}