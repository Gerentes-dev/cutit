package com.devs.cutit.repository;

import com.devs.cutit.model.RequestPartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestPartRepository extends JpaRepository<RequestPartModel, UUID> {
}
