package test.task.employees.entity.view;

public class ProjectViewModel {
    private Long firstEmployee;
    private Long secondEmployee;
    private Long projectId;
    private Long DaysWorked;

    public ProjectViewModel() {
    }

    public Long getFirstEmployee() {
        return firstEmployee;
    }

    public ProjectViewModel setFirstEmployee(Long firstEmployee) {
        this.firstEmployee = firstEmployee;
        return this;
    }

    public Long getSecondEmployee() {
        return secondEmployee;
    }

    public ProjectViewModel setSecondEmployee(Long secondEmployee) {
        this.secondEmployee = secondEmployee;
        return this;
    }

    public Long getProjectId() {
        return projectId;
    }

    public ProjectViewModel setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    public Long getDaysWorked() {
        return DaysWorked;
    }

    public ProjectViewModel setDaysWorked(Long daysWorked) {
        DaysWorked = daysWorked;
        return this;
    }
}
