package com.fuad.manyToMany.employee;

import com.fuad.manyToMany.project.Project;
import com.fuad.manyToMany.project.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> findDetails(Long employeeId) {
        return Optional.ofNullable(employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException()));
    }

    public Employee assignProjectToEmployee(Long empId, Long projectId) {
         //setOfProject = null;
        Employee employee = employeeRepository.findById(empId).get();
        Project project = projectRepository.findById(projectId).get();
        Set<Project> setOfProject = employee.getAssignedProjects();
        setOfProject.add(project);
        employee.setAssignedProjects(setOfProject);
        employeeRepository.save(employee);
        return employee;
    }

    public void delete(Long empId) {
        Employee employee = employeeRepository.findById(empId).get();
        employeeRepository.delete(employee);
    }

    public Employee removeProjectToEmployee(Long empId, Long projectId) {
        Employee employee = employeeRepository.findById(empId).get();
        Project project = projectRepository.findById(projectId).get();
        Set<Project> setOfProject = employee.getAssignedProjects();
        setOfProject.remove(project);
        employee.setAssignedProjects(setOfProject);
        employeeRepository.save(employee);
        return employee;
    }
}
