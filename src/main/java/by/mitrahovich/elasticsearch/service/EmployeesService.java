package by.mitrahovich.elasticsearch.service;

import by.mitrahovich.elasticsearch.entity.Employee;
import by.mitrahovich.elasticsearch.repository.EmployeesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class EmployeesService {
    private EmployeesRepository employeesRepository;

    public List<Employee> getAllEmployees() throws IOException {
        return employeesRepository.getAllEmployees();
    }
}
