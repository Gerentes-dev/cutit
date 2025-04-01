package com.devs.cutit.repository;

import com.devs.cutit.model.MaterialDeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaterialDeliveryRepository extends JpaRepository<MaterialDeliveryModel, UUID> {
  /*  @Query("SELECT SUM(t.quantityDelivered) FROM MaterialDeliveryModel t WHERE t.requestId = :requestId")
    Long obtenerSumaPorRegistro(@Param("requestId") UUID requestId); */

}