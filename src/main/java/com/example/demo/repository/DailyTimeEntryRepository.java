package com.example.demo.repository;

import com.example.demo.model.DailyTimeEntry;
import org.springframework.data.repository.CrudRepository;

public interface DailyTimeEntryRepository extends CrudRepository<DailyTimeEntry,Long> {
}