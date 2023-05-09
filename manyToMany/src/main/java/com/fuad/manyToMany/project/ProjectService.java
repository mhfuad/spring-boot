package com.fuad.manyToMany.project;

import com.fuad.manyToMany.employee.Employee;
import com.fuad.manyToMany.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void delete(Long proId) {
        Project project = projectRepository.findById(proId).get();
        projectRepository.delete(project);
    }

    public Project addEmployeeToProject(Long empId, Long projectId) {

    }

//    public Employee addEmployeeToProject(Long empId, Long projectId) {
//        Employee employee = employeeRepository.findById(empId).get();
//        Project project = projectRepository.findById(projectId).get();
//        Set<Project> projectSet = null;
//    }
}
