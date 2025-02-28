package com.devs.cutit.repository;


import com.devs.cutit.model.PartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartRepository extends JpaRepository<PartModel, UUID> {
}
