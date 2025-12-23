package com.zelexon.recruitiq.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

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

