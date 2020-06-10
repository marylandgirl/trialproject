package com.example.demo.model;



import com.example.demo.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.HashSet;


@SpringBootApplication
public class SpringSecurityJdbcDataSource {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJdbcDataSource.class, args);
    }



    @Bean
    public CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository,
                                 EmployeeRepository employeeRepository, TimeSheetRepository timeSheetRepository,
                                 ManagerRepository managerRepository, DailyTimeEntryRepository dailyTimeEntryRepository,
                                 TestTimeSheetRepository testTimeSheetRepository) throws Exception {
        return(String[] args) -> {



            // insert first user hard code it


            User user = new User("bart", "bart@domain.com", "bart",
                    "Bart", "Simpson", true);

            Role userRole = new Role("bart", "ROLE_USER");
//            for (Interest interests: interestRepository.findAll()){
//                user.getInterests().add(interests);
//            }

            userRepository.save(user);
            roleRepository.save(userRole);

            // second user hard code it
            User admin1 = new User("admin", "ted@domain.com", "admin",
                    "Teddy", "Bear", true);
            Role adminRole1 = new Role("admin", "ROLE_ADMIN");

            userRepository.save(admin1);
            roleRepository.save(adminRole1);


            // insert third user
            User admin = new User("super", "super@domain.com", "super",
                    "Super", "Man", true);
            Role adminRole = new Role("super", "ROLE_ADMIN");

            userRepository.save(admin);
            roleRepository.save(adminRole);

            Role adminRole2 = new Role("super", "ROLE_USER");
            roleRepository.save(adminRole2);



            Manager managerSue = new Manager(new HashSet<Employee>());
            Manager managerOfSue = new Manager(new HashSet<Employee>());
            managerRepository.save(managerOfSue);


            Employee empSue = new Employee("sue", "sue", "Han", "Sue",
                    "sue@mc.edu", 200.00, true, managerOfSue, new HashSet<TimeSheet>());
            Role adminRoleSue = new Role("sue", "ROLE_ADMIN");
            roleRepository.save(adminRoleSue);

            Employee ashu = new Employee("ashu", "ashu", "Maru", "Ashenafi",
                    "ashu@mc.edu", 100.00, true, managerSue, new HashSet<TimeSheet>());
            Role userRoleAshu = new Role("ashu", "ROLE_USER");
            roleRepository.save(userRoleAshu);

            Employee bilen = new Employee("bilen", "bilen", "Worku", "Bilen",
                    "bilen@mc.edu", 100.00, true, managerSue, new HashSet<TimeSheet>());
            Role userRoleBilen = new Role("bilen", "ROLE_USER");
            roleRepository.save(userRoleBilen);

            Employee kim = new Employee("kim", "kim", "Levin", "Kim",
                    "kim@mc.edu", 100.00, true, managerSue, new HashSet<TimeSheet>());
            Role userRoleKim = new Role("kim", "ROLE_USER");
            roleRepository.save(userRoleKim);


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


                        TestTimesheet testTimesheetWeek1 = new TestTimesheet(20, 20,
                                1000.00);
            testTimeSheetRepository.save(testTimesheetWeek1);

TestTimesheet testTimesheetWeek2 = new TestTimesheet(30, 15,
                                2625.00);
            testTimeSheetRepository.save(testTimesheetWeek2);



        };
    }

}
