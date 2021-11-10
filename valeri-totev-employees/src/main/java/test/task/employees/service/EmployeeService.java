package test.task.employees.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
    boolean readEmployees(MultipartFile fileData);
}
