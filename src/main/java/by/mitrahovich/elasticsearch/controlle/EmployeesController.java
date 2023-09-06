package by.mitrahovich.elasticsearch.controlle;

import by.mitrahovich.elasticsearch.entity.Employee;
import by.mitrahovich.elasticsearch.service.EmployeesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping
    public List<Employee> getAllEmployees() throws IOException {
        return employeesService.getAllEmployees();
    }
}
