package test.task.employees.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "EmpID", nullable = false)
    private Long empId;
    @Column(name = "dateFrom")
    private LocalDate dateFrom;
    @Column(name = "dateTo")
    private LocalDate dateTo;

    @ManyToMany(mappedBy = "employees")
    private List<Project> project = new ArrayList<>();

    public Employee() {
    }

    public Long getEmpId() {
        return empId;
    }

    public Employee setEmpId(Long empId) {
        this.empId = empId;
        return this;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public Employee setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Employee setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public List<Project> getProject() {
        return project;
    }

    public Employee setProject(List<Project> project) {
        this.project = project;
        return this;
    }
}
