package com.fuad.manyToMany.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> allEmployee(){
        return employeeService.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Employee employee){
        employeeService.save(employee);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable Long employeeId){
        return new ResponseEntity<>(employeeService.findDetails(employeeId), HttpStatus.OK);
    }

    @PutMapping("/{empId}/assignProject/{projectId}")
    public Employee assignProjectToEmployee(@PathVariable Long empId, @PathVariable Long projectId){
        return employeeService.assignProjectToEmployee(empId, projectId);
    }

    @PutMapping("/{empId}/removeProject/{projectId}")
    public Employee removeProjectToEmployee(@PathVariable Long empId, @PathVariable Long projectId){
        return employeeService.removeProjectToEmployee(empId, projectId);
    }

    @DeleteMapping("/{empId}")
    public String delete(@PathVariable Long empId){
        employeeService.delete(empId);
        return "Delete Success";
    }
}
