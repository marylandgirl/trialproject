package com.example.demo;

import com.example.demo.model.DailyTimeEntry;
import com.example.demo.model.Employee;
import com.example.demo.model.TimeSheet;
import com.example.demo.repository.DailyTimeEntryRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TimeSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;

import javax.validation.Valid;

@Controller
//@RequestMapping("/timesheet")
public class TimeSheetController {

    @Autowired
    DailyTimeEntryRepository  dailyTimeEntryRepository;

    @Autowired
    TimeSheetRepository timeSheetRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    private LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    private LocalDate sunday = monday.plusDays(6);

    static final int DAYS_PER_PAY_PERIOD = 7;

//    @GetMapping("/enterTime/{id}")
    @GetMapping("/enterTime/{id}")
    public String timesheetForm(@PathVariable("id") long id, Employee employee, Model model) {
//        Employee tempUser;
//        tempUser = employeeRepository.findById(id).get();
//
//        TimeSheet tempTimesheet = new TimeSheet();
//        tempTimesheet.set
        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sunday = monday.plusDays(6);
        model.addAttribute("start", monday);
        model.addAttribute("end", sunday);

        WeeklyTimeEntry weeklyTimeEntry = new WeeklyTimeEntry();
        model.addAttribute("timeEntries", weeklyTimeEntry);
//        return "test";
        return "timesheetForm";
    }

    @PostMapping("/processtime")
    public String processTimeSheet(@Valid @ModelAttribute WeeklyTimeEntry weeklyTimeEntry, BindingResult result) {
        TimeSheet timesheet = new TimeSheet();
        timesheet.setStartDate(monday);
        timesheet.setEndDate(sunday);
        timesheet.setRejectMsg("All is Good");
        Employee emp = employeeRepository.findByUserName("kim");
        timesheet.setEmployee(emp);
        DailyTimeEntry[] dailyTimeEntries  = weeklyTimeEntry.updateDailyEntries(monday, sunday, timesheet);
        for (int i = 0; i < dailyTimeEntries.length; i++) {
            try {
                timesheet.getDailyTimeEntrySet().add(dailyTimeEntries[i]);
                timeSheetRepository.save(timesheet);
                //             dailyTimeEntryRepository.save(dailyTimeEntries[i]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "redirect:/";
    }
}