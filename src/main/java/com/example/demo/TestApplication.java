package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TimeSheetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.HashSet;


@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RoleRepository roleRepository, EmployeeRepository employeeRepository, TimeSheetRepository timeSheetRepository,
								 ManagerRepository managerRepository) throws Exception {
		return (String[] args) -> {

			Manager managerSue = new Manager(new HashSet<Employee>());
			Manager managerOfSue = new Manager(new HashSet<Employee>());
			managerRepository.save(managerOfSue);


			Employee empSue = new Employee("admin", "admin", "Han", "Sue",
					"sue@mc.edu", 200.00, true, managerOfSue, new HashSet<TimeSheet>());
			Role adminRoleSue = new Role("admin", "ROLE_ADMIN");
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
			managerSue.setEmpId(empSue.getId());
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

		};
	}
}
