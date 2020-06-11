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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/entertime")
    public String timesheetForm(Model model) {
        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sunday = monday.plusDays(6);
        model.addAttribute("start", monday);
        model.addAttribute("end", sunday);
        WeeklyTimeEntry weeklyTimeEntry = new WeeklyTimeEntry();
        model.addAttribute("timeEntries", weeklyTimeEntry);
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
        return "index";
    }
}