package ua.com.blizartproduction.infohub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "social_responsibility_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialResponsibilityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(columnDefinition = "TEXT", name = "local_impact_description")
    private String localImpactDescription;

    @Column(columnDefinition = "TEXT", name = "local_impact_key_points")
    private String localImpactKeyPoints; // Storing as delimited string or JSON

    @Column(columnDefinition = "TEXT", name = "economic_contribution_description")
    private String economicContributionDescription;

    @Column(columnDefinition = "TEXT", name = "economic_contribution_areas")
    private String economicContributionAreas; // Storing as JSON string

    @Column(columnDefinition = "TEXT", name = "employee_support_description")
    private String employeeSupportDescription;

    @Column(columnDefinition = "TEXT", name = "employee_support_programs")
    private String employeeSupportPrograms; // Storing as delimited string or JSON
}