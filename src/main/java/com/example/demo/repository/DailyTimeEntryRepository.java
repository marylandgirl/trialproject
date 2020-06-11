package com.example.demo.repository;

import com.example.demo.model.TestDailyTimeEntry;
import org.springframework.data.repository.CrudRepository;

public interface DailyTimeEntryRepository extends CrudRepository<TestDailyTimeEntry,Long> {
}
