package com.fuad.manyToMany.project;

import com.fuad.manyToMany.employee.Employee;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Project project){
        projectService.save(project);
        return ResponseEntity.ok("Save successfully");
    }
    @GetMapping("/all")
    public List<Project> allProject(){
        return projectService.getAllProject();
    }

    @DeleteMapping("/{proId}")
    public String delete(@PathVariable Long proId){
        projectService.delete(proId);
        return "Delete successfull";
    }

    @PutMapping("/{projectId}/addEmployee/{empId}")
    public Project addEmployeeToProject(@PathVariable Long projectId, @PathVariable Long empId){
        return projectService.addEmployeeToProject(empId, projectId);
    }
}
