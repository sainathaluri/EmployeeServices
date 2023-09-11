package com.application.employee.service.services.implementations;

import com.application.employee.service.entities.ProjectHistory;
import com.application.employee.service.exceptions.ResourceNotFoundException;
import com.application.employee.service.repositories.ProjectHistoryRepository;
import com.application.employee.service.services.ProjectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class ProjectHistoryServiceImpl implements ProjectHistoryService {
    @Autowired
    private ProjectHistoryRepository projectHistoryRepository;
    @Override
    public ProjectHistory saveProjectHistory(ProjectHistory projectHistory) {
        String randomProjectID = UUID.randomUUID().toString();
        projectHistory.setProjectId(randomProjectID);
        return projectHistoryRepository.save(projectHistory);
    }

    @Override
    public ProjectHistory getProjectHistoryById(String id) {
        return projectHistoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Project History not found with given ID: " + id)
        );
    }

    @Override
    public List<ProjectHistory> getAllProjectHistory() {
        return projectHistoryRepository.findAll();
    }

    @Override
    public ProjectHistory updateProjectHistory(String id, ProjectHistory updateProjectHistory) {
        ProjectHistory existingProject = getProjectHistoryById(id);
        existingProject.setSubVendorOne(updateProjectHistory.getSubVendorOne());
        existingProject.setSubVendorTwo(updateProjectHistory.getSubVendorTwo());
        existingProject.setProjectAddress(updateProjectHistory.getProjectAddress());
        existingProject.setProjectStartDate(updateProjectHistory.getProjectStartDate());
        existingProject.setProjectEndDate(updateProjectHistory.getProjectEndDate());
        existingProject.setProjectStatus(updateProjectHistory.getProjectStatus());
        return projectHistoryRepository.save(existingProject);
    }

    @Override
    public void deleteProjectHistory(String id) {
        ProjectHistory history = getProjectHistoryById(id);
        projectHistoryRepository.delete(history);
    }

    @Override
    public Page<ProjectHistory> findProjectHistoryByEmployee(Pageable pageable) {
        return projectHistoryRepository.findAll(pageable);
    }

}
