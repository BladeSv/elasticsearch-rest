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

    public Employee getEmployeeById(String id) throws IOException {
        return employeesRepository.getEmployeesById(id);
    }

    public List<Employee> getEmployeeByField(String field, String value) throws IOException {
        return employeesRepository.getEmployeesByField(field, value);
    }

    public String createEmployee(Employee employee) throws IOException {
        return employeesRepository.createEmployee(employee);
    }

    public String removeEmployee(String id) throws IOException {
        return employeesRepository.removeEmployee(id);
    }


    public String getAvgAggregation(String metricField, String aggregationField) throws IOException {
        return employeesRepository.getAvgAggregation(metricField, aggregationField);
    }
}
