package com.example.demo.repository;

import com.example.demo.model.TimeSheet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TimeSheetRepository extends CrudRepository<TimeSheet,Long> {
    TimeSheet findByEmployee_Id(long id);
    Set<TimeSheet> findAll();
}
