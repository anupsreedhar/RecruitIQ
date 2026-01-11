package com.zelexon.recruitiq.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    private UUID id;
    private String name;
    private String status;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "vendor")
    private List<VendorUser> users;
}
