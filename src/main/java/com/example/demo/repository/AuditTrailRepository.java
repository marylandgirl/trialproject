package com.example.demo.repository;

import com.example.demo.model.AuditTrail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTrailRepository extends CrudRepository<AuditTrail, Long> {

}
