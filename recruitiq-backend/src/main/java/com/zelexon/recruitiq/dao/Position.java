package com.zelexon.recruitiq.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "positions")
public class Position {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private String title;
    private String jdText;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> jdStructured;

    private String status;
    private LocalDateTime createdAt;
}
