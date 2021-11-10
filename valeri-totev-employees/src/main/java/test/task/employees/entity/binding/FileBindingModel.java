package test.task.employees.entity.binding;

import org.springframework.web.multipart.MultipartFile;

public class FileBindingModel {
    private MultipartFile fileData;

    public FileBindingModel() {
    }

    public MultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }
}
