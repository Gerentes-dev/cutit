package com.devs.cutit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "realizar_requisicion")
public class PerformRequisitionModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "cierra_id", referencedColumnName = "id", nullable = false)
    //private CierraModel cierra;

    @Column(name = "cantidad", nullable = false)
    private Integer quantity;

    @Column(name = "fecha_requerida", nullable = false)
    private LocalDateTime date;

    @Column(name = "observaciones")
    private String remarks;

    @Column(name = "estado", nullable = false)
    private String status;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.status = "Pendiente";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
