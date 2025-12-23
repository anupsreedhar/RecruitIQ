package com.zelexon.recruitiq.dao;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

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

    @Type(type = "jsonb")
    private Map<String, Object> jdStructured;

    private String status;
    private LocalDateTime createdAt;
}

