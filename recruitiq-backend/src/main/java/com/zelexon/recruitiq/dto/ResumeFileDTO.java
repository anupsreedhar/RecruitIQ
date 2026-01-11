package com.zelexon.recruitiq.dto;

import com.zelexon.recruitiq.dao.FileType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "ResumeFileDTO", description = "Resume file metadata")
public class ResumeFileDTO {

    @Schema(description = "Resume file unique identifier", example = "eeeeeeee-1111-2222-3333-ffffffffffff")
    private UUID id;

    @Schema(description = "File type", example = "REDACTED")
    private FileType fileType;

    @Schema(description = "Pre-signed download URL (agency/internal use only)", example = "https://minio.local/bucket/object?X-Amz-Signature=...", nullable = true)
    private String downloadUrl; // For agency use

    @Schema(description = "MIME type", example = "application/pdf")
    private String mimeType;
}
