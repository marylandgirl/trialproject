package com.example.demo.repository;

import com.example.demo.model.TimeSheet;
import org.springframework.data.repository.CrudRepository;

public interface TimeSheetRepository extends CrudRepository<TimeSheet,Long> {
}
