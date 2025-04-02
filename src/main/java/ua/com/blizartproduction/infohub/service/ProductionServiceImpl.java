package ua.com.blizartproduction.infohub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.blizartproduction.infohub.dto.ProductionInfoDto;
import ua.com.blizartproduction.infohub.entity.ProductionInfo;
import ua.com.blizartproduction.infohub.repository.ProductionInfoRepository;
import ua.com.blizartproduction.infohub.exception.ResourceNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductionServiceImpl implements ProductionService {

    private final ProductionInfoRepository productionInfoRepository;
    private static final Long SINGLE_RECORD_ID = 1L;
    private static final String LIST_DELIMITER = ";";


    @Override
    @Transactional(readOnly = true)
    public ProductionInfoDto getProductionInfo() {
        ProductionInfo entity = findSingleRecord();
        return mapEntityToDto(entity);
    }

    @Override
    @Transactional
    public ProductionInfoDto updateProductionInfo(ProductionInfoDto dto) {
        ProductionInfo entity = findSingleRecord();
        mapDtoToEntity(dto, entity);
        ProductionInfo updatedEntity = productionInfoRepository.save(entity);
        return mapEntityToDto(updatedEntity);
    }

    private ProductionInfo findSingleRecord() {
        return productionInfoRepository.findById(SINGLE_RECORD_ID)
                .orElseThrow(() -> new ResourceNotFoundException("Production info record not found"));
    }

    private ProductionInfoDto mapEntityToDto(ProductionInfo entity) {
        List<String> advantages = (entity.getFacilityAdvantages() != null && !entity.getFacilityAdvantages().isBlank())
                ? Arrays.asList(entity.getFacilityAdvantages().split(LIST_DELIMITER))
                : Collections.emptyList();

        return new ProductionInfoDto(
                entity.getMainActivityDescription(), entity.getExportPercentage(), entity.getExportMarkets(),
                entity.getFacilityLocation(), advantages, entity.getProductionShifts(),
                entity.getTechnologyStandards(), entity.getSupplyChainNotes()
        );
    }

    private void mapDtoToEntity(ProductionInfoDto dto, ProductionInfo entity) {
        entity.setMainActivityDescription(dto.getMainActivityDescription());
        entity.setExportPercentage(dto.getExportPercentage());
        entity.setExportMarkets(dto.getExportMarkets());
        entity.setFacilityLocation(dto.getFacilityLocation());
        entity.setFacilityAdvantages(dto.getFacilityAdvantages() != null ? String.join(LIST_DELIMITER, dto.getFacilityAdvantages()) : null);
        entity.setProductionShifts(dto.getProductionShifts());
        entity.setTechnologyStandards(dto.getTechnologyStandards());
        entity.setSupplyChainNotes(dto.getSupplyChainNotes());
    }
}