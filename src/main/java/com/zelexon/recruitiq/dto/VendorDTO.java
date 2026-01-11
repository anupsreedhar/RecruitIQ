package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(name = "VendorDTO", description = "Vendor summary")
public class VendorDTO {

    @Schema(description = "Vendor unique identifier", example = "cccccccc-1111-2222-3333-dddddddddddd")
    private UUID id;

    @Schema(description = "Vendor name", example = "Acme Staffing")
    private String name;
}
