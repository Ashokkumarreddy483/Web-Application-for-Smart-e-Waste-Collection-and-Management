package com.ecocycle.ewastebackend.repository;

import com.ecocycle.ewastebackend.entity.EwasteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EwasteRequestRepository extends JpaRepository<EwasteRequest, Long> {
    List<EwasteRequest> findByUserIdOrderByCreatedAtDesc(Long userId);
}
