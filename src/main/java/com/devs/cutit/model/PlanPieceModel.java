package com.devs.cutit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "planos_piezas")
public class PlanPieceModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plano", referencedColumnName = "id", nullable = false)
    private PlanModel plano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pieza", referencedColumnName = "id", nullable = false)
    private PartModel pieza;

    @Column(name = "cantidad", nullable = false)
    private Integer quantity;
}
