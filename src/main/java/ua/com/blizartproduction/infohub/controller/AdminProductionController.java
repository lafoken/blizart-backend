package ua.com.blizartproduction.infohub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.blizartproduction.infohub.dto.ProductionInfoDto;
import ua.com.blizartproduction.infohub.service.ProductionService;

@RestController
@RequestMapping("/api/admin/production")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminProductionController {

    private final ProductionService productionService;

    @PutMapping("/info")
    public ResponseEntity<ProductionInfoDto> updateProductionInfo(@Valid @RequestBody ProductionInfoDto dto) {
        ProductionInfoDto updatedDto = productionService.updateProductionInfo(dto);
        return ResponseEntity.ok(updatedDto);
    }
}