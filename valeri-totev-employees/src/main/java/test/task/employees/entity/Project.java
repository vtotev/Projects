package test.task.employees.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {
    private Long projectId;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<>();

    public Project() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public Project setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Project setEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }
}
