package com.application.employee.service.controllers;

import com.application.employee.service.entities.ProjectHistory;
import com.application.employee.service.entities.PurchaseOrder;
import com.application.employee.service.services.ProjectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-history")
@CrossOrigin(origins = "*")
public class ProjectHistoryController {
    @Autowired
    private ProjectHistoryService projectHistoryService;
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectHistory> getProjectById(@PathVariable String projectId){
        ProjectHistory history = projectHistoryService.getProjectHistoryById(projectId);
        return ResponseEntity.ok(history);
    }
    @GetMapping
    public ResponseEntity<List<ProjectHistory>> getAllProjectHistory() {
        List<ProjectHistory> historyList = projectHistoryService.getAllProjectHistory();
        return ResponseEntity.ok(historyList);
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProjectHistory(@PathVariable String projectId){
        projectHistoryService.deleteProjectHistory(projectId);
        return ResponseEntity.noContent().build();
    }
}
