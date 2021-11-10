package test.task.employees.service.impl;

import org.springframework.stereotype.Service;
import test.task.employees.entity.Employee;
import test.task.employees.entity.Project;
import test.task.employees.entity.view.ProjectViewModel;
import test.task.employees.repository.ProjectRepository;
import test.task.employees.service.ProjectService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findByProjectId(Long projId) {
        return projectRepository.findByProjectId(projId);
    }

    @Override
    public List<Project> getProjectWithMoreThanOneEmployees() {
        return projectRepository.getAllProjectsWithMoreThanOneEmployee();
    }

    @Override
    public List<ProjectViewModel> getProjectsByEmployeeCouples() {
        List<Project> projects = getProjectWithMoreThanOneEmployees();
        try {
            projects.stream().forEach(p -> {
                for (int i = 0; i < p.getEmployees().size(); i++) {
                    Employee empl = p.getEmployees().get(i);
                    for (int y = 1; y < p.getEmployees().size(); y++) {
                        Employee empl2 = p.getEmployees().get(y);
                        if (empl != empl2 && !isDateOverlapping(empl, empl2)) {
                            p.getEmployees().remove(empl);
                        }
                    }
                }
            });
        } catch (Exception e) {

        }

        List<Project> filtered = projects.stream().filter(p -> p.getEmployees().size() >= 2).toList();
        List<ProjectViewModel> views = new ArrayList<>();
        filtered.forEach(p -> {
            p.getEmployees().sort((o1, o2) -> {
                long o1_days = ChronoUnit.DAYS.between(o1.getDateFrom(), o1.getDateTo());
                long o2_days = ChronoUnit.DAYS.between(o2.getDateFrom(), o2.getDateTo());
                return Long.compare(o2_days, o1_days);
            });
            views.add(new ProjectViewModel().setProjectId(p.getProjectId())
                    .setFirstEmployee(p.getEmployees().get(0).getEmpId())
                    .setSecondEmployee(p.getEmployees().get(1).getEmpId())
                    .setDaysWorked(calculateDays(p.getEmployees().get(0), p.getEmployees().get(1))));
        });
        return views;
    }

    private Long calculateDays(Employee firstEmp, Employee secondEmp) {
        LocalDate dateStart;
        LocalDate dateEnd;
        if (firstEmp.getDateFrom().isBefore(secondEmp.getDateFrom())) {
            dateStart = secondEmp.getDateFrom();
        } else {
            dateStart = firstEmp.getDateFrom();
        }
        if (firstEmp.getDateTo().isAfter(secondEmp.getDateTo())) {
            dateEnd = secondEmp.getDateTo();
        } else {
            dateEnd = firstEmp.getDateTo();
        }
        return ChronoUnit.DAYS.between(dateStart, dateEnd);
    }

    private boolean isDateOverlapping(Employee empl1, Employee empl2) {
        LocalDate empl1DateFrom = empl1.getDateFrom();
        LocalDate empl2DateFrom = empl2.getDateFrom();
        LocalDate empl1DateTo = empl1.getDateTo();
        LocalDate empl2DateTo = empl2.getDateTo();

        if (empl1DateFrom.isBefore(empl2DateFrom) && empl1DateTo.isAfter(empl2DateFrom) ||
                empl1DateFrom.isBefore(empl2DateTo) && empl1DateTo.isAfter(empl2DateTo) ||
                empl1DateFrom.isBefore(empl2DateFrom) && empl1DateTo.isAfter(empl2DateTo) ||
                empl1DateFrom.isAfter(empl2DateFrom) && empl1DateTo.isBefore(empl2DateTo)) {
            return true;
        }
        return false;
    }
}
