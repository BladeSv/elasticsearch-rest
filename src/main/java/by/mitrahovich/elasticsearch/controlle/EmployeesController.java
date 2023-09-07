package by.mitrahovich.elasticsearch.controlle;

import by.mitrahovich.elasticsearch.config.FillerEmployeeIndex;
import by.mitrahovich.elasticsearch.entity.Employee;
import by.mitrahovich.elasticsearch.service.EmployeesService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeesService employeesService;
    private FillerEmployeeIndex fillerEmployeeIndex;

    @GetMapping
    public List<Employee> getAllEmployees() throws IOException {
        return employeesService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) throws IOException {
        return employeesService.getEmployeeById(id);
    }

    @GetMapping("/field")
    public List<Employee> getEmployeeByField(@Parameter(name = "field", required = true) String field,
                                             @Parameter(name = "value", required = true) String value) throws IOException {
        return employeesService.getEmployeeByField(field, value);
    }

    @GetMapping("/aggr")
    public String getAggregation(@Parameter(name = "metricField", description = "Name of metric field", example = "avg_experience", required = true) String metricField,
                                 @Parameter(name = "metricType", description = "Type  of metric", example = "avg", required = true) String metricType,
                                 @Parameter(name = "aggregationField", description = "field of aggregation", example = "experience", required = true) String aggregationField) throws IOException {
        return employeesService.getAggregation(metricField, metricType, aggregationField);
    }

    @PostMapping
    public String createEmployee(@RequestBody Employee employee) throws IOException {
        return employeesService.createEmployee(employee);
    }

    @DeleteMapping
    public String removeEmployee(@RequestParam String id) throws IOException {
        return employeesService.removeEmployee(id);
    }

    @GetMapping("/fillemployeedb")
    public void fillEmployeeDb() throws IOException {
        fillerEmployeeIndex.fillDb();
    }
}
