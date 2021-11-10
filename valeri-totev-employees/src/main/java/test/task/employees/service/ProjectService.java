package test.task.employees.service;

import test.task.employees.entity.Project;
import test.task.employees.entity.view.ProjectViewModel;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project saveProject(Project project);

    Optional<Project> findByProjectId(Long projId);

    List<Project> getProjectWithMoreThanOneEmployees();

    List<ProjectViewModel> getProjectsByEmployeeCouples();
}
