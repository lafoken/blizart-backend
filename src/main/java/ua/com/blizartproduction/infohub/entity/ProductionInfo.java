package ua.com.blizartproduction.infohub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "production_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", name = "main_activity_description")
    private String mainActivityDescription;

    @Column(name = "export_percentage")
    private Integer exportPercentage;

    @Column(name = "export_markets")
    private String exportMarkets;

    @Column(name = "facility_location")
    private String facilityLocation;

    @Column(columnDefinition = "TEXT", name = "facility_advantages")
    private String facilityAdvantages; // Storing as delimited string or JSON

    @Column(name = "production_shifts", length = 100)
    private String productionShifts;

    @Column(columnDefinition = "TEXT", name = "technology_standards")
    private String technologyStandards;

    @Column(columnDefinition = "TEXT", name = "supply_chain_notes")
    private String supplyChainNotes;
}