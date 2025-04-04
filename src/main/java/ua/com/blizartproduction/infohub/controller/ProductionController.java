package ua.com.blizartproduction.infohub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.blizartproduction.infohub.dto.ProductionInfoDto;
import ua.com.blizartproduction.infohub.service.ProductionService;

@RestController
@RequestMapping("/api/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping("/info")
    public ResponseEntity<ProductionInfoDto> getProductionInfo() {
        ProductionInfoDto info = productionService.getProductionInfo();
        return ResponseEntity.ok(info);
    }
}