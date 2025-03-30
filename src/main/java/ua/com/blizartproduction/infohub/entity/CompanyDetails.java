package ua.com.blizartproduction.infohub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false, unique = true, length = 10)
    private String edrpou;

    @Column(nullable = false, name = "legal_address")
    private String legalAddress;

    @Column(name = "director_name", length = 100)
    private String directorName;

    @Column(name = "founding_date", length = 50)
    private String foundingDate;

    @Column(columnDefinition = "TEXT", name = "history_summary")
    private String historySummary;

    @Column(length = 100)
    private String status;

    @Column(name = "parent_company_name")
    private String parentCompanyName;

    @Column(name = "parent_company_address")
    private String parentCompanyAddress;

    @Column(name = "parent_company_website_url")
    private String parentCompanyWebsiteUrl;
}