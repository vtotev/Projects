package test.task.employees.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import test.task.employees.entity.binding.FileBindingModel;
import test.task.employees.entity.view.ProjectViewModel;
import test.task.employees.service.EmployeeService;
import test.task.employees.service.ProjectService;


@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public EmployeeController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/employees/add")
    public String loadEmployees(Model model) {
        if (!model.containsAttribute("fileToUpload")) {
            model.addAttribute("fileToUpload", new FileBindingModel());
        }
        return "employee-load";
    }

    @PostMapping("/employees/add")
    public String loadEmployeesFile(FileBindingModel file) {
        boolean result = employeeService.readEmployees(file.getFileData());
        if (file.getFileData().getOriginalFilename().trim().isEmpty()) {
            return "error";
        }
        return "redirect:/";
    }

    @GetMapping("/employees/get")
    public String getEmployeeCouples(Model model) {
        model.addAttribute("projects", projectService.getProjectsByEmployeeCouples());
        return "projects-list";
    }
}
