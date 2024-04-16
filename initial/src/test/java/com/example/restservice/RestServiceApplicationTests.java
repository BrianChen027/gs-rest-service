package com.example.restservice;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestServiceApplicationTests {

    @Mock
    private EmployeeManager employeeManager;

    // Helper function to get the number of current employees
    int getEmployeeCount(EmployeeManager manager) {
        return manager.getAllEmployees().getEmployeeList().size();
    }

    @BeforeEach
    void setUp() {
        this.employeeManager = new EmployeeManager();
        Employee newEmployee = new Employee("djones3", "Daria", "Jones", "dariajones@gmail.com", "Software developer");
        this.employeeManager.addEmployee(newEmployee);
    }

    @Test
    void createEmployeeManager() {
        EmployeeManager newEmployeeManager = new EmployeeManager();
        Assertions.assertTrue(getEmployeeCount(newEmployeeManager) != 0);
    }

    @Test
    void addEmployee() {
        EmployeeManager manager = new EmployeeManager();
        int initialCount = getEmployeeCount(manager);
        Employee employee = new Employee("djones3", "Daria", "Jones", "dariajones@gmail.com", "Software developer");
        manager.addEmployee(employee);
        Assertions.assertEquals(initialCount + 1, getEmployeeCount(manager));
    }

    @Test
    void employeeIdInList() {
        Assertions.assertTrue(this.employeeManager.getAllEmployees().getEmployeeList().stream()
            .anyMatch(e -> e.getId().equals("djones3")));
    }

    @Test
    void employeeFirstNameInList() {
        Assertions.assertTrue(this.employeeManager.getAllEmployees().getEmployeeList().stream()
            .anyMatch(e -> e.getFirstName().equals("Daria")));
    }

    @Test
    void employeeLastNameInList() {
        Assertions.assertTrue(this.employeeManager.getAllEmployees().getEmployeeList().stream()
            .anyMatch(e -> e.getLastName().equals("Jones")));
    }

    @Test
    void employeeEmailInList() {
        Assertions.assertTrue(this.employeeManager.getAllEmployees().getEmployeeList().stream()
            .anyMatch(e -> e.getEmail().equals("dariajones@gmail.com")));
    }

    @Test
    void employeeTitleInList() {
        Assertions.assertTrue(this.employeeManager.getAllEmployees().getEmployeeList().stream()
            .anyMatch(e -> e.getTitle().equals("Software developer")));
    }
}
