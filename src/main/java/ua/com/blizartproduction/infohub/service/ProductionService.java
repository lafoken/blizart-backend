package ua.com.blizartproduction.infohub.service;

import ua.com.blizartproduction.infohub.dto.ProductionInfoDto;

public interface ProductionService {
    ProductionInfoDto getProductionInfo();
    ProductionInfoDto updateProductionInfo(ProductionInfoDto productionInfoDto); // Додано
}