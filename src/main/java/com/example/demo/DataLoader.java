package com.example.demo;

import com.example.demo.model.DailyTimeEntry;
import com.example.demo.model.Employee;
import com.example.demo.model.Manager;
import com.example.demo.model.TimeSheet;
import com.example.demo.repository.DailyTimeEntryRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.TimeSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.HashSet;

@Controller
public class DataLoader implements CommandLineRunner {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TimeSheetRepository timeSheetRepository;

    @Autowired
    DailyTimeEntryRepository dailyTimeEntryRepository;

    @Override
    public void run(String... strings) throws Exception {

        Manager managerSue = new Manager(new HashSet<Employee>());
        Manager managerOfSue = new Manager(new HashSet<Employee>());
        managerRepository.save(managerOfSue);

        Employee empSue = new Employee("sue", "sue", "Han", "Sue",
                "sue@mc.edu", 200.00, managerOfSue, new HashSet<TimeSheet>());



        Employee ashu = new Employee("ashu", "ashu", "Maru", "Ashuashenafi",
                "ashu@mc.edu", 100.00, managerSue, new HashSet<TimeSheet>());

        Employee bilen = new Employee("bilen", "bilen", "Worku", "Bilen",
                "bilen@mc.edu", 100.00, managerSue, new HashSet<TimeSheet>());

        Employee kim = new Employee("kim", "kim", "Levin", "Kim",
                "kim@mc.edu", 100.00, managerSue, new HashSet<TimeSheet>());

        managerSue.getEmployeeSet().add(ashu);
        managerSue.getEmployeeSet().add(bilen);
        managerSue.getEmployeeSet().add(kim);



        managerRepository.save(managerSue);
        employeeRepository.save(empSue);
        employeeRepository.save(ashu);
        employeeRepository.save(bilen);
        employeeRepository.save(kim);
        managerSue.setEmp_id(empSue.getId());
        managerRepository.save(managerSue);

        TimeSheet ashuTimeSheet1 = new TimeSheet(LocalDate.of(2020, 5, 24),
                LocalDate.of(2020, 5, 30), ashu, 40, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, "", true, new HashSet<DailyTimeEntry>());
        timeSheetRepository.save(ashuTimeSheet1);

        TimeSheet ashuTimeSheet2 = new TimeSheet(LocalDate.of(2020, 5, 31),
                LocalDate.of(2020, 6, 6), ashu, 40, 10, 0,
                0, 0, 0, 0, 0,
                0, 0, "", true, new HashSet<DailyTimeEntry>());
        timeSheetRepository.save(ashuTimeSheet2);

        TimeSheet bilenTimeSheet1 = new TimeSheet(LocalDate.of(2020, 5, 24),
                LocalDate.of(2020, 5, 30), bilen, 40, 3, 0,
                0, 0, 0, 0, 0,
                0, 0, "", true, new HashSet<DailyTimeEntry>());
        timeSheetRepository.save(bilenTimeSheet1);

        TimeSheet bilenTimeSheet2 = new TimeSheet(LocalDate.of(2020, 5, 31),
                LocalDate.of(2020, 6, 6), bilen, 0, 0, 0,
                0, 0, 0, 0, 40,
                0, 0, "", true, new HashSet<DailyTimeEntry>());
        timeSheetRepository.save(bilenTimeSheet2);


        DailyTimeEntry ashuDayThreeWk1 = new DailyTimeEntry(LocalDate.of(2020, 5, 27),
                8, 1, 0, 0, 0, 0,
                0, 0, 0, ashuTimeSheet1);
        dailyTimeEntryRepository.save(ashuDayThreeWk1);

        DailyTimeEntry ashuDayThreeWk2 = new DailyTimeEntry(LocalDate.of(2020, 6, 2),
                4, 0, 0, 0, 0, 0,
                0, 0, 4, ashuTimeSheet2);
        dailyTimeEntryRepository.save(ashuDayThreeWk2);

    }

}
