package com.devs.cutit.repository;


import com.devs.cutit.model.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlanRepository extends JpaRepository<PlanModel, UUID> {
    List<Optional<PlanModel>> findByisValidated(boolean isValidated);
}
