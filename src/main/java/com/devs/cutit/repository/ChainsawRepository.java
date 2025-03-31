package com.devs.cutit.repository;


import com.devs.cutit.model.ChainsawModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChainsawRepository extends JpaRepository<ChainsawModel, UUID> {
}
