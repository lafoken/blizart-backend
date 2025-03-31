package ua.com.blizartproduction.infohub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionInfoDto {
    private String mainActivityDescription;
    private Integer exportPercentage;
    private String exportMarkets;
    private String facilityLocation;
    private List<String> facilityAdvantages;
    private String productionShifts;
    private String technologyStandards;
    private String supplyChainNotes;
}