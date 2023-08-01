package com.application.employee.service.repositories;

import com.application.employee.service.entities.ProjectHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectHistoryRepository extends JpaRepository<ProjectHistory, String> {
}
