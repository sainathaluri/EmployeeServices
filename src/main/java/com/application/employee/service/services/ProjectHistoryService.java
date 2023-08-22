package com.application.employee.service.services;

import com.application.employee.service.entities.Employee;
import com.application.employee.service.entities.ProjectHistory;
import com.application.employee.service.entities.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ProjectHistoryService {
    ProjectHistory saveProjectHistory(ProjectHistory projectHistory);
    ProjectHistory getProjectHistoryById(String id);
    List<ProjectHistory> getAllProjectHistory();
    ProjectHistory updateProjectHistory(String id, ProjectHistory updateProjectHistory);
    void deleteProjectHistory(String id);
    Page<ProjectHistory> findProjectHistoryByEmployee(Pageable pageable);
}
