package com.application.employee.service.services;

import com.application.employee.service.entities.ProjectHistory;
import java.util.List;
public interface ProjectHistoryService {
    ProjectHistory saveProjectHistory(ProjectHistory projectHistory);
    ProjectHistory getProjectHistoryById(String id);
    List<ProjectHistory> getAllProjectHistory();
    ProjectHistory updateProjectHistory(String id, ProjectHistory updateProjectHistory);
    void deleteProjectHistory(String id);
}
