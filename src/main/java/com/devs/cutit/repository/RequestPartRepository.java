package com.devs.cutit.repository;

import com.devs.cutit.model.RequestPartModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestPartRepository extends JpaRepository<RequestPartModel, UUID> {
   /* @Modifying
    @Transactional
    @Query("UPDATE request_parts p SET p.status = :newStatus WHERE p.id = :id")
    int updateStatus(@Param("id") UUID id, @Param("newStatus") String newStatus);*/
}
