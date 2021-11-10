package test.task.employees.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import test.task.employees.entity.Employee;
import test.task.employees.entity.Project;
import test.task.employees.entity.view.ProjectViewModel;
import test.task.employees.repository.EmployeeRepository;
import test.task.employees.service.EmployeeService;
import test.task.employees.service.ProjectService;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectService projectService;
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectService projectService) {
        this.employeeRepository = employeeRepository;
        this.projectService = projectService;
    }

    @Override
    public boolean readEmployees(MultipartFile fileData) {
        List<String> lines = convertFileToString(fileData);
        if (lines.isEmpty()) {
            return false;
        }
        lines
                .stream()
                .forEach(l -> {
                    String[] data = l.split(",\\s+");
                    if (data.length != 4) {
                        return;
                    }
                    Long empId = Long.parseLong(data[0]);
                    Long projId = Long.parseLong(data[1]);
                    LocalDate dateFrom = LocalDate.from(DateTimeFormatter.ofPattern(DATE_PATTERN).parse(data[2]));
                    LocalDate dateTo;

                    try {
                        dateTo = LocalDate.from(DateTimeFormatter.ofPattern(DATE_PATTERN).parse(data[3]));

                    } catch (Exception e) {
                        dateTo = LocalDate.now();
                    }
                    Project project = projectService.findByProjectId(projId).orElse(null);
                    if (project == null) {
                       project = projectService.saveProject(new Project().setProjectId(projId));
                    }
                    Employee employee = new Employee()
                            .setEmpId(empId)
                            .setDateFrom(dateFrom)
                            .setDateTo(dateTo);
                    employeeRepository.save(employee);
                    project.getEmployees().add(employee);

                    projectService.saveProject(project);
                });
        return true;
    }

    private List<String> convertFileToString(MultipartFile fileData) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = fileData.getInputStream();
            try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.stream(sb.toString().split(System.lineSeparator())).collect(Collectors.toList());
    }

}
