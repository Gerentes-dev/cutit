package com.devs.cutit.repository;

import com.devs.cutit.model.PartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PlanPieceRepository extends JpaRepository<PartModel, UUID> {
    @Query("SELECT pp.part FROM PlanPieceModel pp WHERE pp.plan.id = :plan_id")
    List<PartModel> findPartsByPlanoId(@Param("plan_id") UUID planoId);
}
